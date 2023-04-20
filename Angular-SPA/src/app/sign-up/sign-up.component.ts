    import { Component, OnInit } from '@angular/core';
    import { FormBuilder, FormGroup, Validators } from '@angular/forms';
    import { UsersService } from '../services/Users/users.service';
    import { Router } from '@angular/router';


    @Component({
    selector: 'app-sign-up',
    templateUrl: './sign-up.component.html',
    styleUrls: ['./sign-up.component.css']
    })
    export class SignUpComponent implements OnInit {
      registerForm!: FormGroup;

      constructor(private formBuilder: FormBuilder, private users: UsersService, private router: Router) { }

      ngOnInit() {
          this.registerForm = this.formBuilder.group({
          username: ['', Validators.required],
          email: ['', [Validators.required, Validators.email]],
          password: ['', [Validators.required, Validators.minLength(6)]]
          });
      }

      onSubmit() {
          if (this.registerForm.valid) {
              const username = this.registerForm.get('username')!.value;
              const email = this.registerForm.get('email')!.value;
              const password = this.registerForm.get('password')!.value;

             this.users.register(username, email, password)
             this.router.navigate(['/']);
            
      }
  }
}
