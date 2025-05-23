import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RequestSender} from '../request.sender';
import {HeaderComponent} from '../components/header/header.component';
import {ProductCategoryComponent} from '../components/product/product-category/product-category.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {ProductFilterComponent} from '../components/product/filter/product-filter/product-filter.component';

@Component({
    selector: 'app-products-in-category',
    imports: [
        HeaderComponent,
        ProductCategoryComponent,
        ReactiveFormsModule,
        FormsModule,
        NgIf,
        ProductFilterComponent
    ],
    templateUrl: './products-in-category.component.html',
    styleUrl: './products-in-category.component.scss'
})
export class ProductsInCategoryComponent implements OnInit {
    category!: string;
    filter!: any;
    products!: any[];
    numberOfProductsPerPage: number = 30;
    pageNumber: number = 1;
    lastPage!: number;
    productsOnPage!: any[];

    constructor(private route: ActivatedRoute, private sender: RequestSender) {
    }

    ngOnInit(): void {
        this.category = this.route.snapshot.paramMap.get('category')!;
        let filter_url = "http://localhost:8080/filter/";
        let category_url = "http://localhost:8080/category/";
        this.sender.requestGet(filter_url + this.category).subscribe(
            response => {
                this.filter = response.body;
            }
        )
        this.sender.requestGet(category_url + this.category).subscribe(
            response => {
                this.products = response.body;
                this.updateProductsOnPage();
            }
        )
    }

    getCategoryName(): string {
        switch (this.category) {
            case "processor":
                return "Procesory";
            case "graphics_card":
                return "Karty graficzne";
            case "memory":
                return "Pamięci RAM";
            case "motherboard":
                return "Płyty główne";
            case "power_supply":
                return "Zasilacze komputerowe"
            case "ssd":
                return "Dyski SSD";
            case "hdd":
                return "Dyski HDD";
            case "air_cooler":
                return "Chłodzenie powietrzem";
            case "liquid_cooler":
                return "Chłodzenie cieczą";
            case "pc_case":
                return "Obudowy komputerowe";
            default:
                window.location.href = "/page_not_found";
                return "";
        }
    }

    updateProductsOnPage() {
        this.lastPage = Math.ceil(this.products.length / this.numberOfProductsPerPage)
        this.productsOnPage = this.products.slice((this.pageNumber - 1) * this.numberOfProductsPerPage, this.pageNumber * this.numberOfProductsPerPage);
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    }

    prevPage() {
        if (this.pageNumber > 1) {
            this.pageNumber--;
            this.updateProductsOnPage();
        }
    }

    nextPage() {
        if (this.pageNumber * this.numberOfProductsPerPage < this.products.length) {
            this.pageNumber++;
            this.updateProductsOnPage();
        }
    }

    goToFirstPage() {
        this.pageNumber = 1;
        this.updateProductsOnPage();
    }

    goToLastPage() {
        this.pageNumber = this.lastPage;
        this.updateProductsOnPage();
    }

    changeProductsPerPage(event: Event) {
        this.numberOfProductsPerPage = parseInt((event.target as HTMLInputElement).value);
        this.updateProductsOnPage();
    }

    sortProducts(event: Event) {
        let sorting_method = (event.target as HTMLSelectElement).value;
        switch (sorting_method) {
            case "price_asc":
                this.products.sort((a, b) => a.price - b.price);
                break;
            case "price_desc":
                this.products.sort((a, b) => b.price - a.price);
                break;
            case "name_asc":
                this.products.sort((a, b) => a.name.localeCompare(b.name));
                break;
            case "name_desc":
                this.products.sort((a, b) => b.name.localeCompare(a.name));
                break;
            case "default":
                this.products.sort((a, b) => a.id - b.id);
        }
        this.pageNumber = 1;
        this.updateProductsOnPage();
    }

    goToPage(event: Event) {
        let page_number = parseInt(((event.target as HTMLInputElement).value) as string);
        if (page_number > 0 && page_number <= this.lastPage) {
            this.pageNumber = page_number;
            this.updateProductsOnPage();
        }
        (event.target as HTMLInputElement).value = "";
    }
}
