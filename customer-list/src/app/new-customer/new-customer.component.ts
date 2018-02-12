import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';
import { Location } from '@angular/common';

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit {

  constructor(private customerService: CustomerService, private location: Location) { }

  ngOnInit() {
  }

  add(name: string, address: string): void {
    name = name.trim();
    address = address.trim();
    if (!name || !address) { return; }

    let customer = new Customer();
    customer.name = name;
    customer.address = address;

    this.customerService.addCustomer(customer);
    this.goBack();
  }

  goBack(): void {
    this.location.back();
  }

}
