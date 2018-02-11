import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CUSTOMERS } from '../mock-customers';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers = CUSTOMERS;
 
  selectedCustomer: Customer;
 
  constructor() { }
 
  ngOnInit() {
  }
 
  onSelect(customer: Customer): void {
    this.selectedCustomer = customer;
  }

}
