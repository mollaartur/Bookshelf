package com.arturmolla.bookshelf.aspects;

import com.arturmolla.bookshelf.aspects.annotation.RateLimit;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class RateLimitAspect {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Around("@annotation(com.arturmolla.bookshelf.aspects.annotation.RateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimit rateLimited = method.getAnnotation(RateLimit.class);

        String key = ip + ":" + method.getName();
        Bucket bucket = buckets.computeIfAbsent(key, k -> {
            Bandwidth limit = Bandwidth.classic(
                    rateLimited.capacity(),
                    Refill.greedy(rateLimited.refillTokens(), Duration.ofMinutes(rateLimited.refillDurationMinutes()))
            );
            return Bucket.builder().addLimit(limit).build();
        });

        if (bucket.tryConsume(1)) {
            return joinPoint.proceed();
        } else {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            if (response != null) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Too Many Requests");
                response.getWriter().flush();
            }
            return null;
        }
    }
}
