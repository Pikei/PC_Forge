import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-motherboard-filter',
    imports: [
        NgIf
    ],
    templateUrl: './motherboard-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class MotherboardFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        socket: [],
        standard: [],
        chipset: [],
        ram_type: [],
        ram_slots: [],
        ram_cap: [],
        freq: [],
        bt: [],
        wifi: [],
        min_width: number,
        max_width: number,
        min_depth: number,
        max_depth: number
    }

    expandOptions = {
        socket: false,
        standard: false,
        chipset: false,
        ram_type: false,
        ram_slots: false,
        ram_cap: false,
        freq: false
    }
}
