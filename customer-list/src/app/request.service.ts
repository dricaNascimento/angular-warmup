import { Injectable } from '@angular/core';
import { Response, ResponseContentType } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/of'
import { Customer } from './customer';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class RequestService {

  serverUrl : string = "http://192.168.0.103:8080/TesteRestEJB/rest";
  private headers : Headers;

  constructor(private http: HttpClient) {
    // OnInit not supported by services
    this.headers = new Headers({ 'Content-Type': 'application/json' });
    this.headers.append('Accept', 'application/json, text/xml');
    this.headers.append('X-Requested-With', 'XMLHttpRequest');
  }

  simpleGetJson(url: string) : Observable<any> {

    console.log(url);

    let options = new RequestOptions({ headers: this.headers });

    return this.http
      .get<Customer>(this.serverUrl + url);
  }

}