import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-memory-filter',
    imports: [
        NgIf
    ],
    templateUrl: './memory-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class MemoryFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        ram_type: [],
        ram_cap: [],
        freq: [],
        modules: [],
        latency: [],
        light: []
    }

    expandOptions = {
        ram_type: false,
        ram_cap: false,
        freq: false,
        modules: false,
        latency: false
    }
}
