import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';
import {Response} from '@angular/common/http';
import {Dot} from './graph/Dot';
import {Observable} from '../../node_modules/rxjs/Observable';
import { throwError } from '../../node_modules/rxjs';
import '../../node_modules/rxjs/add/operator/map';
import '../../node_modules/rxjs/add/operator/catch';
import '../../node_modules/rxjs/add/observable/throw';
import {SessionService} from './session.service';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "./login/User";

@Injectable({
  providedIn: 'root',
})
export class ApiService {

  constructor(
    private http: HttpClient,
    private session: SessionService
  ) {
  }

  private handleError(error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throwError(error.statusText);
  }

  public getAllDots(): Observable<Dot[]> {
    const headers = new HttpHeaders({

    });
    return this.http
      .get(URL + '/graph/history', {headers: headers})
      .map(response => {
        return response as Dot[];
      }).catch(this.handleError);
  }

  public addDot(dot: Dot): Observable<Dot> {
    const headers = new HttpHeaders({

    });
    return this.http
      .post(URL + '/graph/add-point', dot, {headers: headers})
      .map(response => {
        return response as Dot;
      })
      .catch(this.handleError);
  }

  public deleteAllDots() {
    const headers = new HttpHeaders({

    });
    return this.http
      .post(URL + '/graph/clear-history', null, {headers: headers})
      .catch(this.handleError);
  }

  public signIn(username: string, password: string): Observable<User> {
    var details = {
      'username': username,
      'password': password
    };
    var formBody = [];
    for (var property in details) {
      var encodedKey = encodeURIComponent(property);
      var encodedValue = encodeURIComponent(details[property]);
      formBody.push(encodedKey + "=" + encodedValue);
    }
    let formBody1  =  formBody.join("&");
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    });
    return this.http
      .post(URL + '/login', {body: formBody1},{headers: headers})
      .map(response => {
        return response as User;
      })
    // .catch(this.handleError);
  }


public signUp(username: string, password: string): Observable<User> {
    var form= {
      'username': username,
      'password': password
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http
      .post(URL + '/registration', {body: JSON.stringify(form)}, {headers: headers})
      .map(response => {
        return response as User;
      })
    // .catch(this.handleError);
  }
}

const URL = environment.apiUrl;


