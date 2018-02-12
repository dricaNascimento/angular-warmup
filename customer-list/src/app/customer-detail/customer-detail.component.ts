import { Component, OnInit, Input } from '@angular/core';
import { Customer } from '../customer';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../customer.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})

export class CustomerDetailComponent implements OnInit {

  @Input() customer: Customer;
  
  constructor(private customerService: CustomerService, 
    private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.getCustomer();
  }

  getCustomer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.customerService.getCustomer(id)
      .subscribe(customerParameter => this.customer = customerParameter);
  }

  goBack(): void {
    this.location.back();
  }

  updateCustomer(name: string, address: string) {
    name = name.trim();
    address = address.trim();
    if (!name || !address) { return; }

    let updatedCustomer = new Customer();
    updatedCustomer.id = this.customer.id;
    updatedCustomer.name = name;
    updatedCustomer.address = address;

    this.customerService.updateCustomer(updatedCustomer);
    this.goBack();
  }

}
