import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-auth',
  standalone: false,
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
})
export class AuthComponent {
  isSignUp = false;

  signInForm = {
    email: '',
    password: ''
  };

  signUpForm = {
    username: '',
    email: '',
    password: ''
  };

  toggleSignUp(): void {
    this.isSignUp = !this.isSignUp;
  }

  onSignIn(): void {
    // Implement sign in logic
    console.log('Sign in:', this.signInForm);
  }

  onSignUp(): void {
    // Implement sign up logic
    console.log('Sign up:', this.signUpForm);
  }

  signInWithGoogle(): void {
    // Implement Google sign in logic
    console.log('Google sign in');
  }
}
