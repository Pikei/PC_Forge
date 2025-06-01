import {Component} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';
import {RequestSender} from '../request.sender';
import {NgForOf} from '@angular/common';
import {Router} from '@angular/router';
import {CartService} from '../service/shopping-cart.service';
import {Params} from '../Params';

@Component({
    selector: 'app-config-list',
    imports: [
        HeaderComponent,
        NgForOf
    ],
    templateUrl: './config-list.component.html',
    styleUrl: './config-list.component.scss'
})
export class ConfigListComponent {
    configurations: any[] = [];

    constructor(private sender: RequestSender, private router: Router, private cartService: CartService) {
        sender.requestGet(Params.API_URL + '/configurations/all').subscribe(
            {
                next: response => {
                    this.configurations = response.body;
                }
            }
        );
    }

    getImgSrc(product: any) {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + product.productCategory + "/" + product.producer + "/" + product.productEan + ".png";
    }

    addToCart(config: any) {
        for (let product of config.products) {
            const cartItem = {
                producer: product.producer,
                productCategory: product.productCategory,
                productEan: product.productEan,
                productName: product.productName,
                productPrice: product.productPrice,
                productQuantity: 1
            }
            this.cartService.addProduct(cartItem)
        }
        this.sender.requestPost(Params.API_URL + "/configurations/add_to_cart/" + config.name).subscribe();
    }
}
