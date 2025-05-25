import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from "../components/header/header.component";
import {ActivatedRoute, Router} from '@angular/router';
import {RequestSender} from '../request.sender';
import {NgForOf, NgIf} from '@angular/common';
import {ProductCategoryComponent} from '../components/product/product-category/product-category.component';
import {ReactiveFormsModule} from '@angular/forms';

@Component({
    selector: 'app-product-search-page',
    imports: [
        HeaderComponent,
        NgForOf,
        NgIf,
        ProductCategoryComponent,
        ReactiveFormsModule
    ],
    templateUrl: './product-search-page.component.html',
    styleUrl: './product-search-page.component.scss'
})
export class ProductSearchPageComponent implements OnInit {

    name: string = "";
    products: any[] = [];
    categories: any[] = [];
    numberOfProductsPerPage: number = 30;
    pageNumber: number = 1;
    lastPage!: number;
    productsOnPage!: any[];

    constructor(private route: ActivatedRoute, private sender: RequestSender, private router: Router) {
    }

    ngOnInit(): void {
        this.name = this.route.snapshot.paramMap.get('name') ?? "";
        console.log(this.name)
        this.sender.requestGet('http://localhost:8080/product/search/' + this.name).subscribe(
            response => {
                this.products = response.body;
                for (const product of this.products) {
                    if (!this.categories.includes(product.category)) {
                        this.categories.push(product.category);
                    }
                }
                this.updateProductsOnPage();
            }
        )

    }

    goToCategory(category: any) {
        let path: string = "";
        switch (category) {
            case "CPU":
                path = "processor";
                break;
            case "GPU":
                path = "graphics_card";
                break;
            case "RAM":
                path = "memory";
                break;
            case "MB":
                path = "motherboard";
                break;
            case "PS":
                path = "power_supply";
                break;
            case "SSD":
                path = "ssd";
                break;
            case "HDD":
                path = "hdd";
                break;
            case "AIR_COOLER":
                path = "air_cooler";
                break;
            case "LIQUID_COOLER":
                path = "liquid_cooler";
                break;
            case "CASE":
                path = "pc_case";
                break;
            default:
                path = "";
        }
        this.router.navigate(['/category/' + path], {queryParams: {name: this.name}});
    }

    getCategoryName(category: string) {
        switch (category) {
            case "CPU":
                return "Procesory";
            case "GPU":
                return "Karty graficzne";
            case "RAM":
                return "Pamięci RAM";
            case "MB":
                return "Płyty główne";
            case "PS":
                return "Zasilacze komputerowe"
            case "SSD":
                return "Dyski SSD";
            case "HDD":
                return "Dyski HDD";
            case "AIR_COOLER":
                return "Chłodzenie powietrzem";
            case "LIQUID_COOLER":
                return "Chłodzenie cieczą";
            case "CASE":
                return "Obudowy komputerowe";
            default:
                return category;
        }
    }

    numberOfFoundProducts() {
        return this.products.length;
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
