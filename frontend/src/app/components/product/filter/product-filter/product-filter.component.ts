import {Component, Input} from '@angular/core';
import {CpuFilterComponent} from '../cpu-filter/cpu-filter.component';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-product-filter',
    imports: [
        CpuFilterComponent,
        NgIf
    ],
    templateUrl: './product-filter.component.html',
    styleUrl: './product-filter.component.scss'
})
export class ProductFilterComponent {
    @Input() filter!: any;
    @Input() category!: string;

    expandProducers = false;

    applyFilter() {

    }
}
