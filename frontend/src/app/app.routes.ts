import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './auth/login/login.component';
import {RegisterComponent} from './auth/register/register.component';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {OrderComponent} from './order/order.component';
import {OrderListComponent} from './order-list/order-list.component';
import {PaymentComponent} from './payment/payment.component';
import {ProductPageComponent} from './product-page/product-page.component';
import {ErrorPageComponent} from './error-page/error-page.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'home', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'cart', component: ShoppingCartComponent},
    {path: 'shipping', component: OrderComponent},
    {path: 'orders', component: OrderListComponent},
    {path: 'payment/success', component: PaymentComponent},
    {path: 'product', component: ProductPageComponent},
    {path: 'not_found', component: ErrorPageComponent},

];
