import {Component} from '@angular/core';
import {RequestSender} from '../request.sender';
import {HeaderComponent} from '../components/header/header.component';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductSpecTableComponent} from '../components/product/product-spec-table/product-spec-table.component';
import {NgClass} from '@angular/common';
import {CartService} from '../service/shopping-cart.service';
import {Params} from '../Params';

@Component({
    selector: 'app-product-page',
    imports: [
        HeaderComponent,
        ProductSpecTableComponent,
        NgClass
    ],
    templateUrl: './product-page.component.html',
    styleUrl: './product-page.component.scss'
})
export class ProductPageComponent {
    productEan: number = 0;
    product: any = {};
    zoomImage: boolean = false;

    constructor(private route: ActivatedRoute, private router: Router, private sender: RequestSender, private cartService: CartService) {
        this.route.queryParams.subscribe(params => {
            this.productEan = params['id'];
        })
        this.sender.requestGet(Params.API_URL + '/product/' + this.productEan).subscribe(
            {
                next: response => {
                    this.product = response.body;
                    const descriptionElement = document.querySelector(".description");
                    if (descriptionElement) {
                        descriptionElement.innerHTML += response.body.description;
                    }
                },
                error: err => {
                    this.router.navigate(['/page_not_found']);
                }
            }
        );
    }

    getImgUrl() {
        return "https://cdn.jsdelivr.net/gh/Pikei/PC_Forge_images/" + this.product.category + "/" + this.product.producer + "/" + this.product.id + ".png";
    }

    addToCart() {
        const url = Params.API_URL + '/cart/add'
        const body = {productId: this.productEan}
        this.sender.requestPost(url, body).subscribe();
        const cartItem = {
            producer: this.product.producer,
            productCategory: this.product.category,
            productEan: this.product.id,
            productName: this.product.name,
            productPrice: this.product.price,
            productQuantity: 1
        }
        this.cartService.addProduct(cartItem)
    }

    toggleZoom() {
        this.zoomImage = !this.zoomImage;
    }
}
