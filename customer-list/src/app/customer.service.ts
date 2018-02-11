import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { CUSTOMERS } from './mock-customers';
import { RequestService } from './request.service';
import { Observable } from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class CustomerService {

  customers: Customer[];

  constructor(private http: HttpClient) {}

  getCustomers() {
    return this.http.get<Customer[]>('http://192.168.0.103:8080/TesteRestEJB/rest/customers');
  }

}
