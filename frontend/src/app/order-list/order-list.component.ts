import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';
import {RequestSender} from '../request.sender';
import {NgIf} from '@angular/common';

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

    constructor(private sender: RequestSender) {
        sender.requestGet('http://localhost:8080/order/all').subscribe(
            {
                next: response => {
                    this.orders = response.body;
                },
                error: err => {
                    console.log(err)
                }
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

    protected readonly Math = Math;

    cancelOrder(order: any) {
        const url = 'http://localhost:8080/order/cancel?order_ID=' + order.id;
        this.sender.requestPost(url, null).subscribe(
            response => {
                console.log(response);
                alert(response.body.message);
            });
        this.orders.splice(this.orders.indexOf(order), 1);

    }

    cancellable(order: any): boolean {
        return order.orderStatus != 'Dostarczone' && order.orderStatus != 'Anulowane';
    }

    goToPayment(order: any) {
        let paymentUrl = "http://localhost:8080/order/payment?order_ID=" + order.id;
        this.sender.requestPost(paymentUrl, null).subscribe(response => {
            window.location.href = response.body.message;
        })
    }

    paymentRequired(order: any): boolean {
        return order.orderStatus === "Oczekuje na płatność";
    }

    getImgUrl(product: any) {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + product.productCategory + "/" + product.producer + "/" + product.productEan + ".png";
    }

    getProductSummaryPrice(product: any): number {
        return Math.round(product.productPrice * product.productQuantity * 100) / 100;
    }
}
