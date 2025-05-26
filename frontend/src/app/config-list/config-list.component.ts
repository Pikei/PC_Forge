import {Component} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';
import {RequestSender} from '../request.sender';
import {NgForOf} from '@angular/common';
import {Router} from '@angular/router';

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

    constructor(private sender: RequestSender, private router: Router) {
        sender.requestGet('http://localhost:8080/configurations/all').subscribe(
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

    }

    goToConfig(name: string) {
        this.router.navigate(['/config', name]);
    }

    goToProduct(productEan: number) {
        this.router.navigate(['/product'], {
            queryParams: {id: productEan}
        })
    }
}
