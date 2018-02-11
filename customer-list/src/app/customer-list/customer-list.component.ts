import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CUSTOMERS } from '../mock-customers';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers: Customer[];
 
  selectedCustomer: Customer;
 
  constructor(private customerService: CustomerService ) { }
 
  ngOnInit() {
    this.customerService.getCustomers().subscribe(customers => this.customers = customers);
  }
 
  onSelect(customer: Customer): void {
    this.selectedCustomer = customer;
  }

}
