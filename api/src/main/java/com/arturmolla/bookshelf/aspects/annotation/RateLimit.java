package com.arturmolla.bookshelf.aspects.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    int capacity() default 10;

    int refillTokens() default 10;

    int refillDurationMinutes() default 1;
}
