import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
selector: 'app-sign-up',
templateUrl: './sign-up.component.html',
styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
registerForm!: FormGroup;

constructor(private formBuilder: FormBuilder) { }

ngOnInit() {
this.registerForm = this.formBuilder.group({
username: ['', Validators.required],
email: ['', [Validators.required, Validators.email]],
password: ['', [Validators.required, Validators.minLength(6)]]
});
}

onSubmit() {
console.log(this.registerForm.value);
// Add code to submit form data to server
}
}
