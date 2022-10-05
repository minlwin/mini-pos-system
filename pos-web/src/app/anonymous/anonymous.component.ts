import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginUserService } from '../services/login-user.service';

@Component({
  selector: 'app-anonymous',
  templateUrl: './anonymous.component.html',
  styleUrls: [
    './anonymous.component.css'
  ]
})
export class AnonymousComponent {

  message?:string
  form:FormGroup

  constructor(builder:FormBuilder, private service:LoginUserService, private router:Router) {
    this.form = builder.group({
      email:['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login() {
    if(this.form.valid) {
      this.service.login(this.form.value).subscribe(result => {
        if(result.success) {
          this.router.navigate(['manager'])
        } else {
          this.message = result.message ?? 'Login Error'
        }
      })
    }
  }

}
