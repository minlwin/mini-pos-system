import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { filter, map, of, tap } from 'rxjs';
import { AbstractService } from './abstract.service';

const LOGIN_PATH = "com.jdc.pos.login"

@Injectable({
  providedIn: 'root'
})
export class LoginUserService extends AbstractService{

  loginUser:any

  constructor(private http:HttpClient) {
    super()
  }

  cacheLogin() {
    let userString = localStorage.getItem(LOGIN_PATH)

    if(userString) {
      const user = JSON.parse(userString)
      return this.login(user)
    }

    return of(false)
  }

  login(form:any) {
    return this.http.post<any>(this.basePath('login'), form).pipe(
      filter(a => a.id > 0),
      tap(a => this.loginUser = a),
      tap(() => localStorage.setItem(LOGIN_PATH, JSON.stringify(form)))
    )
  }
}
