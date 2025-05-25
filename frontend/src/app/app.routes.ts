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
import {CategoryPageComponent} from './category-page/category-page.component';
import {ProductsInCategoryComponent} from './products-in-category/products-in-category.component';
import {ProductSearchPageComponent} from './product-search-page/product-search-page.component';
import {VerifyComponent} from './auth/verify/verify.component';
import {ResetComponent} from './auth/reset/reset.component';

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
    {path: 'category', component: CategoryPageComponent},
    {path: 'category/:category', component: ProductsInCategoryComponent},
    {path: 'search/:name', component: ProductSearchPageComponent},
    {path: 'verify', component: VerifyComponent},
    {path: 'reset_password', component: ResetComponent},
    {path: 'page_not_found', component: ErrorPageComponent},
    {path: '**', component: ErrorPageComponent},
];
