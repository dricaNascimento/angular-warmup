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
    const cid = this.route.snapshot.paramMap.get('id');
    this.customerService.getCustomer(cid)
      .subscribe(customerParameter => this.customer = customerParameter);
  }

  goBack(): void {
    this.location.back();
  }

  updateCustomer(name: string, address: string, phone: string) {
    name = name.trim();
    address = address.trim();
    phone = phone.trim();
    if (!name || !address || !phone) { return; }

    let updatedCustomer = new Customer();
    updatedCustomer.cid = this.customer.cid;
    updatedCustomer.name = name;
    updatedCustomer.address = address;
    updatedCustomer.phone = phone;

    this.customerService.updateCustomer(updatedCustomer);
    this.goBack();
  }

}
