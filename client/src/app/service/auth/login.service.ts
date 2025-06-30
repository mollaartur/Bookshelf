import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthStateService } from './auth-state.service';
import { Router } from '@angular/router';

interface AuthRequest {
  email: string;
  password: string;
}

interface RegisterRequest {
  firstname: string;
  lastname: string;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl = 'http://localhost:8088/api/v1/auth/authenticate';
  private registerUrl = 'http://localhost:8088/api/v1/auth/register';
  private googleUrl = 'http://localhost:8088/api/v1/auth/google';

  constructor(private http: HttpClient,
              private authState: AuthStateService,
              private router: Router
  ) {}

  authenticate(authRequest: AuthRequest): Observable<any> {
    return this.http.post<any>(this.loginUrl, authRequest);
  }

  register(authRequest: AuthRequest): Observable<any> {
    return this.http.post<any>(this.registerUrl, authRequest);
  }

  handleGoogleCredential(response: any) {
  const idToken = response.credential;
  
  this.http.post<any>(this.googleUrl, { idToken })
    .subscribe({
      next: (res) => {
        this.authState.setUser({ email: res.email, token: res.token });
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Google login failed:', err);
      }
    });
  }
}
