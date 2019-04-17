import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  email: string;
  password: string;

  ngAfterViewInit() {
    (window as any).initialize();
  }

  login(){
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
    fetch('http://192.168.0.107:27525/login', {
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
    fetch('http://192.168.0.107:27525/registration', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(form)
    }).then(res=>res.json())
    return false;
  }

}
