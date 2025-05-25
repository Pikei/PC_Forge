import {Component} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';
import {NgIf} from '@angular/common';
import {CartService} from '../service/shopping-cart.service';
import {RequestSender} from '../request.sender';
import {
    ShoppingCartProductComponent
} from '../components/product/shopping-cart-product/shopping-cart-product.component';
import {Router} from '@angular/router';

@Component({
    selector: 'app-shopping-cart',
    imports: [
        HeaderComponent,
        NgIf,
        ShoppingCartProductComponent,
    ],
    templateUrl: './shopping-cart.component.html',
    styleUrl: './shopping-cart.component.scss'
})
export class ShoppingCartComponent {

    constructor(public cartItems: CartService, private sender: RequestSender, private router: Router) {
    }

    goToShipping() {
        this.router.navigate(['/shipping']);
    }

    clearCart() {
        this.sender.requestPost('http://localhost:8080/cart/clear', null).subscribe()
        this.cartItems.setProducts([])
    }
}
