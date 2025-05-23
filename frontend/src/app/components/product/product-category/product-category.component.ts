import {Component, inject, Input} from '@angular/core';
import {RequestSender} from '../../../request.sender';
import {CartService} from '../../../service/shopping-cart.service';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-product-category',
    imports: [
        NgIf
    ],
    templateUrl: './product-category.component.html',
    styleUrl: './product-category.component.scss'
})
export class ProductCategoryComponent {
    @Input() product: any;
    // private sender = inject(RequestSender)
    // private cart = inject(CartService)

    constructor(private sender: RequestSender, private cart: CartService) {
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
        window.location.href = "/product?id=" + this.product.id;
    }
}
