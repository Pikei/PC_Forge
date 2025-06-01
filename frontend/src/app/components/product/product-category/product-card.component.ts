import {Component, EventEmitter, inject, Input, Output} from '@angular/core';
import {RequestSender} from '../../../request.sender';
import {CartService} from '../../../service/shopping-cart.service';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';
import {Params} from '../../../Params';

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
    @Input() isConfig: boolean = false;
    @Output() emitter: EventEmitter<any> = new EventEmitter();

    constructor(private sender: RequestSender, private cart: CartService, private router: Router) {
    }

    getImgUrl() {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + this.product.category + "/" + this.product.producer + "/" + this.product.id + ".png";
    }

    addToCart() {
        const url = Params.API_URL + '/cart/add'
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

    addToConfig() {
        this.sender.requestGet(Params.API_URL + '/product/' + this.product.id).subscribe(
            response => {
                this.emitter.emit(response.body);
            }
        );
    }
}
