import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-liquid-cooler-filter',
    imports: [
        NgIf
    ],
    templateUrl: './liquid-cooler-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class LiquidCoolerFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        socket: [],
        fans: [],
        fan_diameter: [],
        noise_lvl: [],
        light: [],
        cooler_size: []
    }

    expandOptions = {
        socket: false,
        fans: false,
        fan_diameter: false,
        noise_lvl: false,
        cooler_size: false
    }
}
