import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers: Customer[];
 
  constructor(private customerService: CustomerService ) { }
 
  ngOnInit() {
    this.customerService.getCustomers().subscribe(customers => this.customers = customers);
  }

  delete(customer: Customer): void {
    this.customers = this.customers.filter(h => h !== customer);
    this.customerService.deleteCustomer(customer);
    //this.customerService.getCustomers().subscribe(customers => this.customers = customers);
    console.log("deletar " + customer.name);
  }

}
