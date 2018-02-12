import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
 
import { CustomerListComponent }   from './customer-list/customer-list.component';
import { CustomerDetailComponent }      from './customer-detail/customer-detail.component';
import { NewCustomerComponent }      from './new-customer/new-customer.component';
 
const routes: Routes = [
  { path: '', redirectTo: '/customers', pathMatch: 'full' },
  { path: 'customers', component: CustomerListComponent },
  { path: 'customer/:id', component: CustomerDetailComponent },
  { path: 'customers/new', component: NewCustomerComponent }
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}