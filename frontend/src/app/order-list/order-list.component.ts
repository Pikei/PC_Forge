import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';
import {RequestSender} from '../service/request.sender';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';
import {Params} from '../Params';

@Component({
    selector: 'app-order-list',
    imports: [
        HeaderComponent,
        NgIf
    ],
    templateUrl: './order-list.component.html',
    styleUrl: './order-list.component.scss'
})
export class OrderListComponent implements OnInit {
    protected orders: any[] = [];

    constructor(private sender: RequestSender, private router: Router) {
        sender.requestGet(Params.API_URL + '/order/all').subscribe(
            response => {
                this.orders = response.body;
            });
    }

    ngOnInit(): void {
    }

    numberOfProductsInOrder(order: any) {
        let productCount: number = 0;
        for (const product of order.orderedProducts) {
            productCount += product.productQuantity
        }
        return productCount;
    }

    cancelOrder(order: any) {
        const url = Params.API_URL + '/order/cancel?order_ID=' + order.id;
        this.sender.requestPost(url, null).subscribe(
            response => {
                alert(response.body.message);
            });
        this.orders.splice(this.orders.indexOf(order), 1);

    }

    cancellable(order: any): boolean {
        return order.orderStatus != 'Dostarczone' && order.orderStatus != 'Anulowane';
    }

    goToPayment(order: any) {
        let paymentUrl = Params.API_URL + "/order/payment?order_ID=" + order.id;
        this.sender.requestPost(paymentUrl, null).subscribe(response => {
            window.open(response.body.message, '_blank');
        })
    }

    paymentRequired(order: any): boolean {
        return order.orderStatus === "Oczekuje na płatność";
    }

    getImgUrl(product: any) {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + product.productCategory + "/" + product.producer + "/" + product.productEan + ".png";
    }

    getProductSummaryPrice(product: any): string {
        return (product.productPrice * product.productQuantity).toFixed(2);
    }

    getNumberOfOrders() {
        return this.orders.length;
    }
}
