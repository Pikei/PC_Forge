import {Injectable} from '@angular/core';
import {TokenService} from './token.service';
import {Router} from '@angular/router';

@Injectable({providedIn: 'root'})
export class CartService {
    private products: {
        producer: string,
        productCategory: string,
        productEan: number,
        productName: string,
        productPrice: number,
        productQuantity: number
    }[] = [];

    constructor(private tokenService: TokenService, private router: Router) {
    }

    setProducts(products: any[]) {
        this.products = products;
    }

    getProducts(): any[] {
        return this.products;
    }

    getTotalCost(): string {
        let totalCost = 0;
        for (let i = 0; i < this.products.length; i++) {
            totalCost += this.products[i].productPrice * this.products[i].productQuantity;
        }
        return totalCost.toFixed(2);
    }

    getCounter(): number {
        let productCounter = 0;
        for (let i = 0; i < this.products.length; i++) {
            productCounter += this.products[i].productQuantity;
        }
        return productCounter;
    }

    addProduct(product: any) {
        if (!this.tokenService.getToken()) {
            this.router.navigate(['/login']);
        } else {
            for (let i = 0; i < this.products.length; i++) {
                if (this.products[i].productEan === product.productEan) {
                    this.products[i].productQuantity++;
                    return;
                }
            }
            this.products.push(product);
        }
    }

    removeProduct(productId: number) {
        for (let i = 0; i < this.products.length; i++) {
            if (this.products[i].productEan === productId) {
                this.products.splice(i, 1);
                break;
            }
        }
    }

    incrementProductQuantity(productId: number) {
        for (let i = 0; i < this.products.length; i++) {
            if (this.products[i].productEan === productId) {
                this.products[i].productQuantity++;
                break;
            }
        }
    }

    decrementProductQuantity(productId: number) {
        for (let i = 0; i < this.products.length; i++) {
            if (this.products[i].productEan === productId) {
                this.products[i].productQuantity--;
                break;
            }
        }
    }
}
