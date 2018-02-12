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

  getCustomer(id: number): Observable<Customer> {
    const url = `/customers/${id}`;
    return this.requestService.simpleGetJson(url);
  }

  addCustomer(customer: Customer): void {
    this.requestService.post('/customers/new', customer);
  }

  deleteCustomer(customer: Customer): void {
    if(customer) {
      const id = customer.id;
      const url = `/customers/delete/${id}`;
      this.requestService.delete(url);
    }
  }

}
