import {Component, OnInit} from '@angular/core';
import {
    ShoppingCartProductComponent
} from '../components/product/shopping-cart-product/shopping-cart-product.component';
import {HeaderComponent} from '../components/header/header.component';
import {RequestSender} from '../request.sender';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-shopping-cart',
    imports: [
        ShoppingCartProductComponent,
        HeaderComponent,
        NgIf
    ],
    templateUrl: './shopping-cart.component.html',
    styleUrl: './shopping-cart.component.scss'
})
export class ShoppingCartComponent implements OnInit {
    productCount: number = 0;
    productList: any[] = [];

    constructor(private sender: RequestSender) {
    }

    ngOnInit(): void {
        this.sender.requestGet('http://localhost:8080/cart').subscribe(
            {
                next: response => {
                    for (let i = 0; i < response.body.length; i++) {
                        this.productCount += response.body[i].productQuantity;
                        this.productList.push(response.body[i]);
                    }
                },
                error: err => {
                    console.log(err)
                }
            });
        console.log(this.productList);
    }

}
