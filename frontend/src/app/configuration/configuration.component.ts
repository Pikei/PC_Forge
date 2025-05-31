import {AfterViewInit, Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HeaderComponent} from '../components/header/header.component';
import {RequestSender} from '../request.sender';
import {NgForOf, NgIf} from '@angular/common';
import {ProductConfigComponent} from '../components/product/product-config/product-config.component';
import {CoolerChoiceComponent} from '../components/product/cooler-choice/cooler-choice.component';
import {CartService} from '../service/shopping-cart.service';
import {FormsModule} from '@angular/forms';
import {ProductCardComponent} from '../components/product/product-category/product-card.component';
import {ProductFilterComponent} from '../components/product/filter/product-filter/product-filter.component';

@Component({
    selector: 'app-configuration',
    imports: [
        HeaderComponent,
        NgIf,
        ProductConfigComponent,
        CoolerChoiceComponent,
        FormsModule,
        ProductFilterComponent,
        NgForOf,
        ProductCardComponent
    ],
    templateUrl: './configuration.component.html',
    styleUrl: './configuration.component.scss'
})
export class ConfigurationComponent implements AfterViewInit {
    configName: string = "";
    chosenCooler: string = "";
    currentCategoryCode: string = "";
    categoryFiler: any;
    activeFilters: Map<string, string[]> = new Map()
    products: any[] = [];
    numberOfProductsPerPage: number = 30;
    pageNumber: number = 1;
    lastPage!: number;
    productsOnPage!: any[];
    name: string = "";
    config: {
        name: string | null,
        price: number | null,
        processor: any
        motherboard: any
        memory: any
        graphicsCard: any
        powerSupply: any
        pcCase: any
        cooler: any
        solidStateDrive: any
        hardDiskDrive: any
    } = {
        name: null,
        price: null,
        processor: null,
        motherboard: null,
        memory: null,
        graphicsCard: null,
        powerSupply: null,
        pcCase: null,
        cooler: null,
        solidStateDrive: null,
        hardDiskDrive: null
    };

    constructor(private route: ActivatedRoute, private router: Router, private sender: RequestSender, private cartService: CartService) {
        this.configName = this.route.snapshot.paramMap.get('configName')!;
        if (this.configName == "new") {
            this.configName = "";
        } else {
            this.sender.requestGet('http://localhost:8080/configurations/config/' + this.configName).subscribe(
                {
                    next: response => {
                        this.config = response.body;
                        this.chosenCooler = this.config.cooler.category;
                    },
                    error: () => {
                        router.navigate(['/page_not_found']);
                    }
                }
            );
        }
    }

    ngAfterViewInit(): void {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    }

    addToCart() {
        const url = 'http://localhost:8080/configurations/add_to_cart/' + this.configName;
        this.sender.requestPost(url, null).subscribe();

        let cartItem = {
            producer: '',
            productCategory: '',
            productEan: 0,
            productName: '',
            productPrice: 0,
            productQuantity: 0
        }

        let productsInConfiguration: {
            producer: string,
            productCategory: string,
            productEan: number,
            productName: string,
            productPrice: number,
            productQuantity: number
        }[] = [];

        if (this.config.processor != null) {
            cartItem.producer = this.config.processor.producer;
            cartItem.productCategory = this.config.processor.category;
            cartItem.productEan = this.config.processor.id;
            cartItem.productName = this.config.processor.name;
            cartItem.productPrice = this.config.processor.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.motherboard != null) {
            cartItem.producer = this.config.motherboard.producer;
            cartItem.productCategory = this.config.motherboard.category;
            cartItem.productEan = this.config.motherboard.id;
            cartItem.productName = this.config.motherboard.name;
            cartItem.productPrice = this.config.motherboard.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.memory != null) {
            cartItem.producer = this.config.memory.producer;
            cartItem.productCategory = this.config.memory.category;
            cartItem.productEan = this.config.memory.id;
            cartItem.productName = this.config.memory.name;
            cartItem.productPrice = this.config.memory.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.graphicsCard != null) {
            cartItem.producer = this.config.graphicsCard.producer;
            cartItem.productCategory = this.config.graphicsCard.category;
            cartItem.productEan = this.config.graphicsCard.id;
            cartItem.productName = this.config.graphicsCard.name;
            cartItem.productPrice = this.config.graphicsCard.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.powerSupply != null) {
            cartItem.producer = this.config.powerSupply.producer;
            cartItem.productCategory = this.config.powerSupply.category;
            cartItem.productEan = this.config.powerSupply.id;
            cartItem.productName = this.config.powerSupply.name;
            cartItem.productPrice = this.config.powerSupply.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.pcCase != null) {
            cartItem.producer = this.config.pcCase.producer;
            cartItem.productCategory = this.config.pcCase.category;
            cartItem.productEan = this.config.pcCase.id;
            cartItem.productName = this.config.pcCase.name;
            cartItem.productPrice = this.config.pcCase.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.cooler != null) {
            cartItem.producer = this.config.cooler.producer;
            cartItem.productCategory = this.config.cooler.category;
            cartItem.productEan = this.config.cooler.id;
            cartItem.productName = this.config.cooler.name;
            cartItem.productPrice = this.config.cooler.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.solidStateDrive != null) {
            cartItem.producer = this.config.solidStateDrive.producer;
            cartItem.productCategory = this.config.solidStateDrive.category;
            cartItem.productEan = this.config.solidStateDrive.id;
            cartItem.productName = this.config.solidStateDrive.name;
            cartItem.productPrice = this.config.solidStateDrive.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }
        if (this.config.hardDiskDrive != null) {
            cartItem.producer = this.config.hardDiskDrive.producer;
            cartItem.productCategory = this.config.hardDiskDrive.category;
            cartItem.productEan = this.config.hardDiskDrive.id;
            cartItem.productName = this.config.hardDiskDrive.name;
            cartItem.productPrice = this.config.hardDiskDrive.price;
            cartItem.productQuantity = 1;
            productsInConfiguration.push(cartItem);
        }

        for (const product of productsInConfiguration) {
            this.cartService.addProduct(product);
        }
    }

    getSummaryPrice() {
        if (this.config.price != null) {
            return (Math.abs(this.config.price)).toFixed(2) + " zł";
        }
        return "";
    }

    removeProduct(categoryCode: string) {
        this.currentCategoryCode = '';
        switch (categoryCode) {
            case "CPU":
                this.config.price! -= this.config.processor.price;
                this.config.processor = null;
                break;
            case "GPU":
                this.config.price! -= this.config.graphicsCard.price;
                this.config.graphicsCard = null;
                break;
            case "RAM":
                this.config.price! -= this.config.memory.price;
                this.config.memory = null;
                break;
            case "MB":
                this.config.price! -= this.config.motherboard.price;
                this.config.motherboard = null;
                break;
            case "PS":
                this.config.price! -= this.config.powerSupply.price;
                this.config.powerSupply = null;
                break;
            case "SSD":
                this.config.price! -= this.config.solidStateDrive.price;
                this.config.solidStateDrive = null;
                break;
            case "HDD":
                this.config.price! -= this.config.hardDiskDrive.price;
                this.config.hardDiskDrive = null;
                break;
            case "AIR_COOLER":
                this.config.price! -= this.config.cooler.price;
                this.config.cooler = null;
                this.chosenCooler = '';
                break;
            case "LIQUID_COOLER":
                this.config.price! -= this.config.cooler.price;
                this.config.cooler = null;
                this.chosenCooler = '';
                break;
            case "CASE":
                this.config.price! -= this.config.pcCase.price;
                this.config.pcCase = null;
                break;
        }
    }

    editProduct($event: string) {
        this.currentCategoryCode = $event;
        const filter_url = "http://localhost:8080/filter/";
        const category_url = "http://localhost:8080/category/";
        this.sender.requestGet(filter_url + $event).subscribe(
            response => {
                this.categoryFiler = response.body;
            }
        )
        let paramUrl = new Array<string>();
        for (const [key, value] of this.activeFilters) {
            paramUrl.push(key + "=" + value);
        }
        let paramUrlString = "";
        if (paramUrl.length > 0) {
            paramUrlString = "?" + paramUrl.join("&");
        }
        this.sender.requestGet(category_url + this.currentCategoryCode + paramUrlString).subscribe(
            response => {
                this.products = response.body;
                this.updateProductsOnPage();
            }
        )
    }

    setCoolerCategory($event: string) {
        this.chosenCooler = $event;
    }

    saveConfig() {

    }

    getFilteredProducts($event: string) {
        const url = "http://localhost:8080/category/" + this.currentCategoryCode + $event;
        this.sender.requestGet(url).subscribe(response => {
            this.products = response.body;
            this.pageNumber = 1;
            this.updateProductsOnPage();
        })
    }

    updateProductsOnPage() {
        this.lastPage = Math.ceil(this.products.length / this.numberOfProductsPerPage)
        this.productsOnPage = this.products.slice((this.pageNumber - 1) * this.numberOfProductsPerPage, this.pageNumber * this.numberOfProductsPerPage);
        document.querySelector('.price-wrapper')?.scrollIntoView({behavior: "smooth"});
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

    addProductToConfig($event: any) {
        console.log(this.activeFilters)
        switch ($event.category) {
            case "CPU":
                this.config.processor = $event;
                this.activeFilters.set("socket", [this.config.processor.socket]);
                document.querySelector('#cpu_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "GPU":
                this.config.graphicsCard = $event;
                document.querySelector('#gpu_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "RAM":
                this.config.memory = $event;
                this.activeFilters.set("ram_type", [this.config.memory.memoryType]);
                document.querySelector('#ram_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "MB":
                this.config.motherboard = $event;
                document.querySelector('#mb_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "PS":
                this.config.powerSupply = $event;
                document.querySelector('#ps_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "SSD":
                this.config.solidStateDrive = $event;
                document.querySelector('#ssd_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "HDD":
                this.config.hardDiskDrive = $event;
                document.querySelector('#hdd_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "AIR_COOLER":
                this.config.cooler = $event;
                document.querySelector('#cooler_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "LIQUID_COOLER":
                this.config.cooler = $event;
                document.querySelector('#cooler_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "CASE":
                this.config.pcCase = $event;
                document.querySelector('#case_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
        }
        this.currentCategoryCode = '';
    }

    getActiveFilters() {
        return this.activeFilters;
    }
}
