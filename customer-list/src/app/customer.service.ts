import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { RequestService } from './request.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CustomerService {
  constructor(private requestService: RequestService) {}

  getCustomers() {
    return this.requestService.simpleGetJson('/customers');
  }

  getCustomer(id: string): Observable<Customer> {
    const url = `/customers/${id}`;
    return this.requestService.simpleGetJson(url);
  }

  addCustomer(customer: Customer): void {
    this.requestService.post('/customers/new', customer);
  }

  updateCustomer(customer: Customer): void {
    const url = '/customers/update';
    this.requestService.put(url, customer);
  }

  deleteCustomer(customer: Customer): void {
    if(customer) {
      const customer_id = customer.cid;
      const url = `/customers/delete/${customer_id}`;
      this.requestService.delete(url);
    }
  }

}
