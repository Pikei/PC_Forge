import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-air-cooler-filter',
    imports: [
        NgIf
    ],
    templateUrl: './air-cooler-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class AirCoolerFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        socket: [],
        fans: [],
        fan_diameter: [],
        noise_lvl: [],
        light: [],
        base: [],
        min_height: number,
        max_height: number,
        min_width: number,
        max_width: number,
        min_depth: number,
        max_depth: number,
        vert: [],
    }

    expandOptions = {
        socket: false,
        fans: false,
        fan_diameter: false,
        noise_lvl: false,
        base: false
    }
}
