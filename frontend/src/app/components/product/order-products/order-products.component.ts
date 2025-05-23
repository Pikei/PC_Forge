import {Component} from '@angular/core';
import {CartService} from '../../../service/shopping-cart.service';

@Component({
    selector: 'app-order-products',
    imports: [],
    templateUrl: './order-products.component.html',
    styleUrl: './order-products.component.scss'
})
export class OrderProductsComponent {
    constructor(protected cartItems: CartService) {
    }

    getImgUrl(cartItem: any): string {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + cartItem.productCategory + "/" + cartItem.producer + "/" + cartItem.productEan + ".png";
    }

    getProductSummaryPrice(cartItem: any) {
        return (cartItem.productPrice * cartItem.productQuantity).toFixed(2);
    }
}
