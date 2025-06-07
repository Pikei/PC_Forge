import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {CpuFilterComponent} from '../cpu-filter/cpu-filter.component';
import {NgIf} from '@angular/common';
import {MotherboardFilterComponent} from '../motherboard-filter/motherboard-filter.component';
import {MemoryFilterComponent} from '../memory-filter/memory-filter.component';
import {GpuFilterComponent} from '../gpu-filter/gpu-filter.component';
import {PsFilterComponent} from '../ps-filter/ps-filter.component';
import {SsdFilterComponent} from '../ssd-filter/ssd-filter.component';
import {HddFilterComponent} from '../hdd-filter/hdd-filter.component';
import {CaseFilterComponent} from '../case-filter/case-filter.component';
import {LiquidCoolerFilterComponent} from '../liquid-cooler-filter/liquid-cooler-filter.component';
import {AirCoolerFilterComponent} from '../air-cooler-filter/air-cooler-filter.component';
import {Params} from '../../../../Params';

@Component({
    selector: 'app-product-filter',
    imports: [
        CpuFilterComponent,
        NgIf,
        MotherboardFilterComponent,
        MemoryFilterComponent,
        GpuFilterComponent,
        PsFilterComponent,
        SsdFilterComponent,
        HddFilterComponent,
        CaseFilterComponent,
        LiquidCoolerFilterComponent,
        AirCoolerFilterComponent
    ],
    templateUrl: './product-filter.component.html',
    styleUrl: './product-filter.component.scss'
})
export class ProductFilterComponent implements OnInit, AfterViewInit {
    @Input() filter: any;
    @Input() category!: string;
    @Input() activeFilters!: Map<string, string[]>;
    @ViewChild('productFilter') productFilter?: any;
    @Output() filterApplied = new EventEmitter<string>();

    expandProducers = false;
    selectedProducers: string[] = [];
    name: string = "";

    ngOnInit(): void {
        let paramUrl = []
        for (const [key, value] of this.activeFilters) {
            paramUrl.push(key + "=" + value);
        }
    }

    ngAfterViewInit(): void {
        this.productFilter?.setFilters(this.activeFilters);
        if (this.activeFilters.has(Params.PRODUCER)) {
            this.selectedProducers = this.activeFilters.get(Params.PRODUCER)![0].split(',');
        }
        if (this.activeFilters.has(Params.PRICE_MINIMUM)) {
            let priceMin = (document.querySelector("#price_min") as HTMLInputElement);
            priceMin.value = "";
        }
        if (this.activeFilters.has(Params.PRICE_MAXIMUM)) {
            let priceMax = (document.querySelector("#price_max") as HTMLInputElement);
            priceMax.value = "";
        }
        if (this.activeFilters.has(Params.NAME)) {
            this.name = this.activeFilters.get(Params.NAME)![0];
        }
    }

    applyFilter() {
        const priceMin = (document.querySelector("#price_min") as HTMLInputElement)?.value;
        const priceMax = (document.querySelector("#price_max") as HTMLInputElement)?.value;
        let filter = this.productFilter?.getSelectedFilters();
        if (this.selectedProducers.length > 0) {
            filter.set(Params.PRODUCER, this.selectedProducers);
        } else {
            filter.delete(Params.PRODUCER);
        }
        let paramUrl: string[] = [];
        for (const [key, value] of filter) {
            paramUrl.push(key + "=" + value);
        }
        if (priceMin != "") {
            paramUrl.push(Params.PRICE_MINIMUM + "=" + priceMin);
        }
        if (priceMax != "") {
            paramUrl.push(Params.PRICE_MAXIMUM + "=" + priceMax);
        }
        if (paramUrl.length == 0) {
            this.filterApplied.emit("");
            return;
        }
        if (this.name != "") {
            paramUrl.push(Params.NAME + "=" + this.name);
        } else {
            filter.delete(Params.NAME);
        }
        let paramUrlString = "?" + paramUrl.join("&");
        this.filterApplied.emit(paramUrlString)
    }

    toggleProducer(producer: string) {
        if (this.selectedProducers.includes(producer)) {
            this.selectedProducers.splice(this.selectedProducers.indexOf(producer), 1);
        } else {
            this.selectedProducers.push(producer);
        }
    }

    getMinPrice() {
        return this.filter.min_price;
    }

    getMaxPrice() {
        return this.filter.max_price;
    }

    clearPrice() {
        let priceMin = (document.querySelector("#price_min") as HTMLInputElement);
        let priceMax = (document.querySelector("#price_max") as HTMLInputElement);
        priceMax.value = "";
        priceMin.value = "";
    }

    clearAllFilters() {
        let priceMin = (document.querySelector("#price_min") as HTMLInputElement);
        let priceMax = (document.querySelector("#price_max") as HTMLInputElement);
        priceMin.value = "";
        priceMax.value = "";
        this.selectedProducers = [];
        this.productFilter?.clearFilters();
        this.name = "";
        this.filterApplied.emit("");
    }

    producerSelected(producer: string) {
        return this.selectedProducers.includes(producer);
    }

    searchName() {
        return this.name != "";
    }

    clearSearch() {
        this.name = "";
        if (this.activeFilters.has(Params.NAME)) {
            this.activeFilters.delete(Params.NAME);
            this.applyFilter();
        }
    }

    getSearch() {
        return this.name;
    }

    protected readonly Params = Params;
}
