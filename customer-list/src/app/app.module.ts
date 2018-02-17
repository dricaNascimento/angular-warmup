import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerDetailComponent } from './customer-detail/customer-detail.component';
import { CustomerService } from './customer.service';
import { RequestService } from './request.service';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { PhonePipe } from './phone.pipe';

@NgModule({
  declarations: [
    AppComponent,
    CustomerListComponent,
    CustomerDetailComponent,
    NewCustomerComponent,
    NavigationBarComponent,
    PhonePipe
  ],
  imports: [
    BrowserModule,
    FormsModule, HttpClientModule, AppRoutingModule
  ],
  providers: [RequestService, CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
