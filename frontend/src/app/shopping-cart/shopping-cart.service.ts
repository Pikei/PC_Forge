import {Injectable} from '@angular/core';

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

    setProducts(products: any[]) {
        this.products = products;
    }

    getProducts(): any[] {
        return this.products;
    }

    getTotalCost(): number {
        let totalCost = 0;
        for (let i = 0; i < this.products.length; i++) {
            totalCost += this.products[i].productPrice * this.products[i].productQuantity;
        }
        return Math.round(totalCost * 100) / 100;
    }

    getCounter(): number {
        let productCounter = 0;
        for (let i = 0; i < this.products.length; i++) {
            productCounter += this.products[i].productQuantity;
        }
        return productCounter;
    }

    addProduct(product: any) {
        this.products.push(product);
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
