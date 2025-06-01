import {Component, Input, OnInit} from '@angular/core';
import {RequestSender} from '../../../request.sender';
import {CartService} from '../../../service/shopping-cart.service';
import {Router} from '@angular/router';
import {Params} from '../../../Params';

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

    constructor(private sender: RequestSender, protected cartItems: CartService, private router: Router) {
    }

    ngOnInit(): void {
        this.imgSrc += this.product.productCategory + "/" + this.product.producer + "/" + this.product.productEan + ".png";
    }

    removeFromCart() {
        this.sender.requestPost(Params.API_URL + "/cart/remove", {productId: this.product.productEan}).subscribe()
        if (this.product.productQuantity > 0) {
            this.cartItems.decrementProductQuantity(this.product.productEan);
        }
    }

    addToCart() {
        this.sender.requestPost(Params.API_URL + "/cart/add", {productId: this.product.productEan}).subscribe()
        this.cartItems.incrementProductQuantity(this.product.productEan);
    }

    clearFromCart() {
        this.sender.requestPost(Params.API_URL + "/cart/clear/product", {productId: this.product.productEan}).subscribe()
        this.cartItems.removeProduct(this.product.productEan);
    }

    totalProductPrice(): string {
        return (this.product.productPrice * this.product.productQuantity).toFixed(2);
    }

    goToProduct() {
        this.router.navigate(['/product'], {
            queryParams: {id: this.product.productEan}
        })
    }
}
