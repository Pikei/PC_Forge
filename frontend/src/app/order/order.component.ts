import {Component} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';
import {CartService} from '../service/shopping-cart.service';
import {RequestSender} from '../request.sender';

@Component({
    selector: 'app-order',
    imports: [
        HeaderComponent,
    ],
    templateUrl: './order.component.html',
    styleUrl: './order.component.scss'
})
export class OrderComponent {
    constructor(protected cartItems: CartService, private sender: RequestSender) {
    }

    goToPayment() {
        const orderBody = {
            "city": (document.querySelector("#city") as HTMLInputElement)?.value,
            "street": (document.querySelector("#street") as HTMLInputElement)?.value,
            "houseNumber": (document.querySelector("#houseNumber") as HTMLInputElement)?.value,
            "apartmentNumber": (document.querySelector("#apartmentNumber") as HTMLInputElement)?.value,
            "postalCode": (document.querySelector("#postalCode") as HTMLInputElement)?.value,
        }
        console.log(orderBody)
        this.sender.requestPost('http://localhost:8080/order/new', orderBody).subscribe(
            response => {
                window.open(response.body.message, '_blank')
            });
    }

    getImgUrl(cartItem: any): string {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + cartItem.productCategory + "/" + cartItem.producer + "/" + cartItem.productEan + ".png";
    }

    getProductSummaryPrice(cartItem: any) {
        return (cartItem.productPrice * cartItem.productQuantity).toFixed(2);
    }
}
