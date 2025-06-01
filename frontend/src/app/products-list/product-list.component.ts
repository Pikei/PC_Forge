import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RequestSender} from '../request.sender';
import {HeaderComponent} from '../components/header/header.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';
import {ProductFilterComponent} from '../components/product/filter/product-filter/product-filter.component';
import {ProductCardComponent} from '../components/product/product-category/product-card.component';
import {Params} from '../Params';

@Component({
    selector: 'app-product-list',
    imports: [
        HeaderComponent,
        ReactiveFormsModule,
        FormsModule,
        NgIf,
        ProductFilterComponent,
        NgForOf,
        ProductCardComponent
    ],
    templateUrl: './product-list.component.html',
    styleUrl: './product-list.component.scss'
})
export class ProductListComponent implements OnInit {
    category!: string;
    filter!: any;
    products: any[] = [];
    numberOfProductsPerPage: number = 30;
    pageNumber: number = 1;
    lastPage!: number;
    productsOnPage!: any[];
    paramMap: Map<string, string[]> = new Map()
    modeCategory: boolean = false;
    modeSearch: boolean = false;
    name: string = "";
    categories: any[] = [];


    constructor(private route: ActivatedRoute, private router: Router, private sender: RequestSender) {
    }

    ngOnInit(): void {
        this.initMode();
        if (this.modeCategory) {
            this.initCategoryView();
        } else if (this.modeSearch) {
            this.initSearchView();
        } else {
            this.router.navigate(['/page_not_found']);
        }
    }

    private initMode() {
        if (this.route.snapshot.paramMap.has('categoryName')) {
            this.category = this.route.snapshot.paramMap.get('categoryName')!;
            this.modeCategory = true;
            return;
        }
        if (this.route.snapshot.url.at(0)?.path == 'search') {
            this.modeSearch = true;
            if (this.route.snapshot.queryParams['name']) {
                this.name = this.route.snapshot.queryParams['name'];
            }
        }
    }

    private initCategoryView() {
        this.route.queryParamMap.subscribe(params => {
            this.paramMap = new Map<string, string[]>();
            params.keys.forEach(key => {
                const values = params.getAll(key) || [];
                this.paramMap.set(key, values);
            });
        })
        let filter_url = Params.API_URL + "/filter/";
        let category_url = Params.API_URL + "/category/";
        this.sender.requestGet(filter_url + this.getCategoryCode()).subscribe(
            response => {
                this.filter = response.body;
            }
        )
        let paramUrl = new Array<string>();
        for (const [key, value] of this.paramMap) {
            paramUrl.push(key + "=" + value);
        }
        let paramUrlString = "";
        if (paramUrl.length > 0) {
            paramUrlString = "?" + paramUrl.join("&");
        }
        this.sender.requestGet(category_url + this.getCategoryCode() + paramUrlString).subscribe(
            response => {
                this.products = response.body;
                this.updateProductsOnPage();
            }
        )
    }

    private initSearchView() {
        this.sender.requestGet(Params.API_URL + '/product/search/' + this.name).subscribe(
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

    isCategoryMode(): boolean {
        return this.modeCategory;
    }

    isSearchMode(): boolean {
        return this.modeSearch;
    }

    getCategoryNameFromUrl(): string {
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
                return "";
        }
    }

    getCategoryNameFromCode(categoryCode: string): string {
        switch (categoryCode) {
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
                return "";
        }
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

    getFilteredProducts(event: string) {
        let category_url = Params.API_URL + "/category/";
        const queryParams = this.parseParams(event);
        if (event != "") {
            this.router.navigate(['category/' + this.category], {
                queryParams
            });
        } else {
            this.router.navigate(['category/' + this.category]);
        }
        this.sender.requestGet(category_url + this.getCategoryCode() + event).subscribe(
            response => {
                this.products = response.body;
                this.pageNumber = 1;
                this.updateProductsOnPage();
            }
        )
    }

    numberOfProductsInCategory(): number {
        return this.products.length;
    }

    getCategoryCode() {
        switch (this.category) {
            case "processor":
                return "CPU";
            case "graphics_card":
                return "GPU";
            case "memory":
                return "RAM";
            case "motherboard":
                return "MB";
            case "power_supply":
                return "PS"
            case "ssd":
                return "SSD";
            case "hdd":
                return "HDD";
            case "air_cooler":
                return "AIR_COOLER";
            case "liquid_cooler":
                return "LIQUID_COOLER";
            case "pc_case":
                return "CASE";
            default:
                this.router.navigate(['/page_not_found']);
        }
        return "";
    }

    private parseParams(event: string) {
        let result: { [key: string]: string } = {};
        let params = event.substring(1).split("&");
        for (const param of params) {
            let [key, value] = param.split("=");
            if (key && value !== undefined) {
                result[key] = decodeURIComponent(value);
            }
        }
        return result;
    }


    getCategories() {
        return this.categories;
    }
}
