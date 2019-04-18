import { Component, OnInit } from '@angular/core';
import {User} from './User';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {ApiService} from '../api.service'
import {Observable} from '../../../node_modules/rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {throwError} from 'rxjs';
import {headersToString} from 'selenium-webdriver/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(
    private _router: Router,
    private api: ApiService,
    private auth: AuthService,
  ) {
  }

  email: string;
  password: string;
  submitted = false;
  model = new User(this.email, this.password);
  failed = false;
  busy = false;
  notExist = false;


  ngAfterViewInit() {
    (window as any).initialize();
  }

  newUser() {
    this.model = new User(this.email, this.password);
  }

   public login() {
     this._router.navigate(['/graph']);
     this.api
       .signIn(this.model.username, this.model.password)
       .subscribe(
         (response) => {
           this.auth.doSignIn(
             response.username,
             response.password,
           );

           this._router.navigate(['/graph']);
         },
         (error) => {
           if (error.status == 418) {
             this.notExist = true;
           } else {
             this.failed = true;
           }
         }
       );
   }
   public signUp() {
     this.api
       .signUp(this.model.username, this.model.password)
       .subscribe(
         (response) => {
           if (response != null) {
             this.auth.doSignIn(
               response.username,
               response.password,
             );
           } else {
             this.busy = true;
           }
           this._router.navigate(['/graph']);
         },
         (error) => {
           this.failed = true;
         }
       );
   }
 }

/*login(){
  var details = {
    'username': this.email,
    'password': this.password
  };
  var formBody = [];
  for (var property in details) {
    var encodedKey = encodeURIComponent(property);
    var encodedValue = encodeURIComponent(details[property]);
    formBody.push(encodedKey + "=" + encodedValue);
  }
  let formBody1  =  formBody.join("&");
  fetch('http://localhost:27525/login', {
    method: 'post',
    redirect: 'follow',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: formBody1
  })
}
signUp(){
  var form= {
    'username': this.email,
    'password': this.password
  };
  fetch('http://localhost:27525/registration', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(form)
  }).then(res=>res.json())
  return false;
} */


