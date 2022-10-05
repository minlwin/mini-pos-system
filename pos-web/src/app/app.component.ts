import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUserService } from './services/login-user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent implements OnInit{

  constructor(private service:LoginUserService, private router:Router) {}

  ngOnInit(): void {
    this.service.cacheLogin().subscribe(result => this.router.navigate([result ? 'manager' : 'anonymous']))
  }
}
