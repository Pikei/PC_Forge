import {Component, Input, OnInit} from '@angular/core';
import {RequestSender} from '../../../request.sender';
import {HeaderComponent} from '../../header/header.component';

@Component({
    selector: 'app-shopping-cart-product',
    imports: [],
    templateUrl: './shopping-cart-product.component.html',
    styleUrl: './shopping-cart-product.component.scss'
})
export class ShoppingCartProductComponent implements OnInit {
    @Input() product: {
        producer: string,
        productCategory: string,
        productEan: number,
        productName: string,
        productPrice: number,
        productQuantity: number,
    } = {
        producer: '',
        productCategory: '',
        productEan: 0,
        productName: '',
        productPrice: 0,
        productQuantity: 0
    };

    imgSrc: string = "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/";

    constructor(private sender: RequestSender) {
    }

    ngOnInit(): void {
        this.imgSrc += this.product.productCategory + "/" + this.product.producer + "/" + this.product.productEan + ".png";
    }

    removeFromCart() {
        this.sender.requestPost("http://localhost:8080/cart/remove", {productId: this.product.productEan}).subscribe()
        window.location.reload();
    }

    addToCart() {
        this.sender.requestPost("http://localhost:8080/cart/add", {productId: this.product.productEan}).subscribe()
        window.location.reload();
    }

    clearFromCart() {
        this.sender.requestPost("http://localhost:8080/cart/clear/product", {productId: this.product.productEan}).subscribe()
        window.location.reload();
    }

    totalProductPrice(): number {
        return Math.round(this.product.productPrice * this.product.productQuantity * 100) / 100;
    }
}
