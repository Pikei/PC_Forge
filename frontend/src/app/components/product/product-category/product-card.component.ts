import {Component, inject, Input} from '@angular/core';
import {RequestSender} from '../../../request.sender';
import {CartService} from '../../../service/shopping-cart.service';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';

@Component({
    selector: 'app-product-card',
    imports: [
        NgIf
    ],
    templateUrl: './product-card.component.html',
    styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {
    @Input() product: any;

    constructor(private sender: RequestSender, private cart: CartService, private router: Router) {
    }

    getImgUrl() {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + this.product.category + "/" + this.product.producer + "/" + this.product.id + ".png";
    }

    addToCart() {
        const url = 'http://localhost:8080/cart/add'
        const body = {productId: this.product.id}
        this.sender.requestPost(url, body).subscribe();
        const cartItem = {
            producer: this.product.producer,
            productCategory: this.product.category,
            productEan: this.product.id,
            productName: this.product.name,
            productPrice: this.product.price,
            productQuantity: 1
        }
        this.cart.addProduct(cartItem)
    }

    goToProductUrl() {
        this.router.navigate(['/product'], {
            queryParams: {id: this.product.id}
        });
    }
}
