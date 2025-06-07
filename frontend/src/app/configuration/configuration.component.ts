import {AfterViewInit, Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HeaderComponent} from '../components/header/header.component';
import {RequestSender} from '../request.sender';
import {NgForOf, NgIf} from '@angular/common';
import {ProductConfigComponent} from '../components/product/product-config/product-config.component';
import {CoolerChoiceComponent} from '../components/product/cooler-choice/cooler-choice.component';
import {CartService} from '../service/shopping-cart.service';
import {FormsModule} from '@angular/forms';
import {ProductCardComponent} from '../components/product/product-card/product-card.component';
import {ProductFilterComponent} from '../components/product/filter/product-filter/product-filter.component';
import {Params} from '../Params';

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
            this.sender.requestGet(Params.API_URL + '/configurations/config/' + this.configName).subscribe(
                {
                    next: response => {
                        this.config = response.body;
                        this.chosenCooler = this.config.cooler.category;
                        this.activeFilters.set(Params.SOCKET, this.config.processor.socket);
                        this.activeFilters.set(Params.MOTHERBOARD_STANDARD, this.config.motherboard.standard);
                        this.activeFilters.set(Params.RAM_TYPE, this.config.memory.memoryType);
                        this.activeFilters.set(Params.RAM_FREQUENCY, this.config.memory.frequency);
                        console.log(this.config);
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
        const url = Params.API_URL + '/configurations/add_to_cart/' + this.configName;
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
                this.removeCpu();
                break;
            case "GPU":
                this.removeGpu();
                break;
            case "RAM":
                this.removeRam();
                break;
            case "MB":
                this.removeMb();
                break;
            case "PS":
                this.removePs();
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
                this.removeAirCooler();
                break;
            case "LIQUID_COOLER":
                this.removeLiquidCooler()
                break;
            case "CASE":
                this.removeCase();
                break;
        }
    }

    editProduct($event: string) {
        this.currentCategoryCode = $event;
        this.resetInvalidProduct($event);
        const filter_url = Params.API_URL + "/filter/";
        const category_url = Params.API_URL + "/category/";
        this.sender.requestGet(filter_url + $event).subscribe(
            response => {
                this.categoryFiler = response.body;
            }
        )
        let paramUrl = new Array<string>();
        for (const [key, value] of this.getActiveFilters()) {
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

    checkIfConfigExists() {
        let configNameInput = document.querySelector('#config_name') as HTMLInputElement;
        if (configNameInput.value == "") {
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
            configNameInput.classList.add('invalid');
            configNameInput.placeholder = "Wpisz nazwę konfiguracji";
            configNameInput.style.border = "2px solid red";
            return;
        }
        this.sender.requestGet(Params.API_URL + "/configurations/exists/" + configNameInput.value).subscribe(
            response => {
                if (response.body) {
                    if (confirm("Konfiguracja o tej nazwie już istnieje. Czy chcesz ją nadpisać?")) {
                        this.saveConfig(configNameInput.value);
                    }
                } else {
                    this.saveConfig(configNameInput.value);
                }
            }
        )
    }

    getFilteredProducts($event: string) {
        const url = Params.API_URL + "/category/" + this.currentCategoryCode + $event;
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
        console.log(this.getActiveFilters());
        console.log(this.categoryFiler)
        switch ($event.category) {
            case "CPU":
                this.config.processor = $event;
                this.setCpuFilters();
                document.querySelector('#cpu_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "GPU":
                this.config.graphicsCard = $event;
                this.setGpuFilters();
                document.querySelector('#gpu_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "RAM":
                this.config.memory = $event;
                this.setMemoryFilters();
                document.querySelector('#ram_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "MB":
                this.config.motherboard = $event;
                this.setMotherboardFilters();
                document.querySelector('#mb_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "PS":
                this.config.powerSupply = $event;
                this.setPowerSupplyFilters();
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
                this.setCoolerFilters();
                document.querySelector('#cooler_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "LIQUID_COOLER":
                this.config.cooler = $event;
                this.setCoolerFilters();
                document.querySelector('#cooler_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            case "CASE":
                this.config.pcCase = $event;
                this.setCaseFilters();
                document.querySelector('#case_wrapper')?.scrollIntoView({behavior: "smooth"});
                break;
            default:
                break;
        }
        this.currentCategoryCode = '';
    }

    getActiveFilters() {
        return this.activeFilters
    }

    private setCpuFilters() {
        if (!this.activeFilters.has(Params.SOCKET)) {
            this.activeFilters.set(Params.SOCKET, [this.config.processor.socket]);
        }
    }

    private setMemoryFilters() {
        if (!this.activeFilters.has(Params.RAM_TYPE)) {
            this.activeFilters.set(Params.RAM_TYPE, [this.config.memory.memoryType]);
        }
        if (!this.activeFilters.has(Params.RAM_FREQUENCY)) {
            this.activeFilters.set(Params.RAM_FREQUENCY, [this.config.memory.frequency]);
        }
        if (!this.activeFilters.has(Params.RAM_CAPACITY)) {
            this.activeFilters.set(Params.RAM_CAPACITY, [this.config.memory.maxMemoryCapacity]);
        }
        for (let cap of this.categoryFiler[Params.RAM_CAPACITY]) {
            if (cap[0] <= this.config.memory.maxMemoryCapacity && !this.activeFilters.get(Params.RAM_CAPACITY)?.includes(cap[0])) {
                this.activeFilters.get(Params.RAM_CAPACITY)?.push(cap[0]);
            }
        }
    }

    private setMotherboardFilters() {
        if (this.config.processor == null) {
            if (!this.activeFilters.has(Params.SOCKET)) {
                this.activeFilters.set(Params.SOCKET, [this.config.motherboard.socket]);
            }
        } else {
            this.setCpuFilters();
        }
        if (this.config.memory == null) {
            if (!this.activeFilters.has(Params.RAM_TYPE)) {
                this.activeFilters.set(Params.RAM_TYPE, [this.config.motherboard.memoryStandard]);
            }
            if (!this.activeFilters.has(Params.RAM_FREQUENCY)) {
                this.activeFilters.set(Params.RAM_FREQUENCY, this.config.motherboard[Params.RAM_FREQUENCY]);
            }
        } else {
            this.setMemoryFilters();
        }
        if (!this.activeFilters.has(Params.MOTHERBOARD_STANDARD)) {
            this.activeFilters.set(Params.MOTHERBOARD_STANDARD, [this.config.motherboard[Params.MOTHERBOARD_STANDARD]]);
        }
    }

    private setPowerSupplyFilters() {
        if (!this.activeFilters.has(Params.PS_POWER)) {
            this.activeFilters.set(Params.PS_POWER, [this.config.powerSupply.power]);
            for (let pow of this.categoryFiler.power) {
                if (pow[0] <= this.config.powerSupply.power) {
                    this.activeFilters.get(Params.PS_POWER)?.push(pow[0]);
                }
            }
        }
    }

    private setCoolerFilters() {
        if (!this.activeFilters.has(Params.SOCKET)) {
            this.activeFilters.set(Params.SOCKET, this.config.cooler.socket);
        }
    }

    private setCaseFilters() {
        if (!this.activeFilters.has(Params.MOTHERBOARD_STANDARD)) {
            this.activeFilters.set(Params.MOTHERBOARD_STANDARD, this.config.pcCase[Params.MOTHERBOARD_STANDARD]);
        }
        if (this.config.pcCase[Params.HAS_POWER_SUPPLY] == true && !this.activeFilters.has(Params.PS_POWER)) {
            if (!this.activeFilters.has(Params.PS_POWER)) {
                this.activeFilters.set(Params.PS_POWER, [this.config.pcCase.psPower]);
            }
            for (let pow of this.categoryFiler.power) {
                if (pow[0] <= this.config.pcCase.psPower && !this.activeFilters.get(Params.PS_POWER)?.includes(pow[0]) && pow[0] > 0) {
                    this.activeFilters.get(Params.PS_POWER)?.push(pow[0]);
                }
            }
        }
        this.activeFilters.set(Params.MAX_CPU_HEIGHT, [this.config.pcCase.maxCpuCoolerHeight])
        this.activeFilters.set(Params.MAX_GPU_LENGTH, [this.config.pcCase.maxGpuLength])
    }

    private setGpuFilters() {
        if (!this.activeFilters.has(Params.PS_POWER)) {
            this.activeFilters.set(Params.PS_POWER, [this.config.graphicsCard.recommendedPsPower]);
            for (let pow of this.categoryFiler[Params.PS_POWER]) {
                if (pow[0] >= this.config.graphicsCard.recommendedPsPower) {
                    this.activeFilters.get(Params.PS_POWER)?.push(pow[0]);
                }
            }
        }
    }

    removeInvalidClass() {
        let configNameInput = document.querySelector('#config_name') as HTMLInputElement;
        configNameInput.classList.remove('invalid');
        configNameInput.style.border = "2px solid #dadada";
    }

    private saveConfig(configName: string) {
        if (this.config.processor == null ||
            this.config.motherboard == null ||
            this.config.memory == null ||
            this.config.pcCase == null
        ) {
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
            alert("Nie wybrano wszystkich wymaganych produktów.")
            return;
        }
        let cpuId = this.config.processor.id;
        let mbId = this.config.motherboard.id;
        let ramId = this.config.memory.id;
        let caseId = this.config.pcCase.id;
        let gpuId = this.config.graphicsCard != null ? this.config.graphicsCard.id : null;
        let coolerId = this.config.cooler != null ? this.config.cooler.id : null;
        let psId = this.config.powerSupply != null ? this.config.powerSupply.id : null;
        let ssdId = this.config.solidStateDrive != null ? this.config.solidStateDrive.id : null;
        let hddId = this.config.hardDiskDrive != null ? this.config.hardDiskDrive.id : null;

        let configBody = {
            "configName": configName,
            "processorId": cpuId,
            "motherboardId": mbId,
            "memoryId": ramId,
            "caseId": caseId,
            "graphicsCardId": gpuId,
            "coolerId": coolerId,
            "powerSupplyId": psId,
            "solidStateDriveId": ssdId,
            "hardDiskDriveId": hddId
        }
        this.sender.requestPost(Params.API_URL + "/configurations/save", configBody).subscribe(
            {
                next: () => {
                    alert("Konfiguracja została zapisana.");
                    this.router.navigate(['/config_list']);
                },
                error: err => this.notCompatible(err.error)
            }
        )
    }

    private removeCpu() {
        this.config.price! -= this.config.processor.price;
        this.config.processor = null;
        if (this.config.motherboard == null && this.activeFilters.has(Params.SOCKET)) {
            this.activeFilters.delete(Params.SOCKET);
            this.activeFilters.delete(Params.SOCKET);
            this.activeFilters.delete(Params.SOCKET);
        }
    }

    private removeGpu() {
        this.config.price! -= this.config.graphicsCard.price;
        this.config.graphicsCard = null;
        if (this.config.powerSupply == null && this.activeFilters.has(Params.PS_POWER)) {
            this.activeFilters.delete(Params.PS_POWER);
            this.activeFilters.delete(Params.PS_POWER);
        }
    }

    private removeRam() {
        this.config.price! -= this.config.memory.price;
        this.config.memory = null;
        if (this.config.motherboard == null && this.activeFilters.has(Params.RAM_TYPE)) {
            this.activeFilters.delete(Params.RAM_TYPE);
            this.activeFilters.delete(Params.RAM_TYPE);
        }
        if (this.config.motherboard == null && this.activeFilters.has(Params.RAM_FREQUENCY)) {
            this.activeFilters.delete(Params.RAM_FREQUENCY);
            this.activeFilters.delete(Params.RAM_FREQUENCY);
        }
        if (this.config.motherboard == null && this.activeFilters.has(Params.RAM_CAPACITY)) {
            this.activeFilters.delete(Params.RAM_CAPACITY);
            this.activeFilters.delete(Params.RAM_CAPACITY);
        }
    }

    private removeMb() {
        this.config.price! -= this.config.motherboard.price;
        this.config.motherboard = null;
        if (this.config.memory == null && this.activeFilters.has(Params.RAM_TYPE)) {
            this.activeFilters.delete(Params.RAM_TYPE);
            this.activeFilters.delete(Params.RAM_TYPE);
        }
        if (this.config.memory == null && this.activeFilters.has(Params.RAM_FREQUENCY)) {
            this.activeFilters.delete(Params.RAM_FREQUENCY);
            this.activeFilters.delete(Params.RAM_FREQUENCY);
        }
        if (this.config.memory == null && this.activeFilters.has(Params.RAM_CAPACITY)) {
            this.activeFilters.delete(Params.RAM_CAPACITY);
            this.activeFilters.delete(Params.RAM_CAPACITY);
        }
        if (this.config.processor == null && this.activeFilters.has(Params.SOCKET)) {
            this.activeFilters.delete(Params.SOCKET);
            this.activeFilters.delete(Params.SOCKET);
        }
        if (this.config.pcCase == null && this.activeFilters.has(Params.MOTHERBOARD_STANDARD)) {
            this.activeFilters.delete(Params.MOTHERBOARD_STANDARD);
            this.activeFilters.delete(Params.MOTHERBOARD_STANDARD);
        }
    }

    private removePs() {
        this.config.price! -= this.config.powerSupply.price;
        this.config.powerSupply = null;
        if (this.config.graphicsCard == null && this.activeFilters.has(Params.PS_POWER)) {
            this.activeFilters.delete(Params.PS_POWER);
            this.activeFilters.delete(Params.PS_POWER);
        }
    }

    private removeAirCooler() {
        this.config.price! -= this.config.cooler.price;
        this.config.cooler = null;
        if (this.config.pcCase == null && this.activeFilters.has(Params.MAX_CPU_HEIGHT)) {
            this.activeFilters.delete(Params.MAX_CPU_HEIGHT)
        }
        if (this.config.motherboard == null && this.config.processor == null) {
            this.activeFilters.delete(Params.SOCKET)
        }
    }

    private removeLiquidCooler() {

        this.config.price! -= this.config.cooler.price;
        this.config.cooler = null;

        if (this.config.motherboard == null && this.config.processor == null) {
            this.activeFilters.delete(Params.SOCKET)
        }
    }

    private removeCase() {

        this.config.price! -= this.config.pcCase.price;
        this.config.pcCase = null;

        this.activeFilters.delete(Params.MAX_GPU_LENGTH)
        this.activeFilters.delete(Params.MAX_CPU_HEIGHT)
        if (this.config.motherboard == null) {
            this.activeFilters.delete(Params.MOTHERBOARD_STANDARD)
        }

    }

    private notCompatible(error: any) {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        })
        alert(error.message);
        this.invalidProduct(error.firstProductCategoryCode);
        this.invalidProduct(error.secondProductCategoryCode);
    }

    private invalidProduct(categoryCode: string) {
        try {
            this.removeProduct(categoryCode);
        } catch (error) {
            console.log(error);
        }
        let selector = this.getProductSelector(categoryCode);
        let element = document.querySelector(selector) as HTMLElement;
        element.classList.add('invalid');
        element.style.border = "2px solid red";
        element.style.borderRadius = "25px";
    }

    private resetInvalidProduct(categoryCode: string) {
        let selector = this.getProductSelector(categoryCode);
        let element = document.querySelector(selector) as HTMLElement;
        element.classList.remove('invalid');
        element.style.border = "none"
        element.style.borderBottom = "2px solid #353535";
        element.style.borderRadius = "0";
    }

    private getProductSelector(categoryCode: string) {
        switch (categoryCode) {
            case "CPU":
                return "#cpu_wrapper";
            case "GPU":
                return "#gpu_wrapper";
            case "RAM":
                return "#ram_wrapper";
            case "MB":
                return "#mb_wrapper";
            case "PS":
                return "#ps_wrapper";
            case "SSD":
                return "#ssd_wrapper";
            case "HDD":
                return "#hdd_wrapper";
            case "AIR_COOLER":
                return "#cooler_wrapper";
            case "LIQUID_COOLER":
                return "#cooler_wrapper";
            case "CASE":
                return "#case_wrapper";
        }
        throw new Error("Invalid category code");
    }
}
