import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class RequestService {
  serverUrl : string = "http://192.168.0.103:8080/TesteRestEJB/rest";

  constructor(private http: HttpClient) {}

  simpleGetJson(url: string) : Observable<any> {
    console.log('RequestService simpleGetJson url: ' +  url);
    return this.http
      .get(this.serverUrl + url);
  }

  post (url: string, entity: any) {
    console.log('RequestService post url: ' +  url);
    return this.http.post<any>(this.serverUrl + url, entity, httpOptions)
      .subscribe();
  }

  delete(url: string) {
    console.log('RequestService delete url: ' +  url);
    return this.http.delete(this.serverUrl + url, httpOptions)
      .subscribe();
  }

}