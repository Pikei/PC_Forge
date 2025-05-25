import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-ps-filter',
    imports: [
        NgIf
    ],
    templateUrl: './ps-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'

})
export class PsFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        power: [],
        cert: [],
        efficiency: [],
        protections: [],
        cooling_type: [],
        fan_diameter: [],
        modular: [],
        light: []
    }

    expandOptions = {
        power: false,
        cert: false,
        efficiency: false,
        protections: false,
        cooling_type: false,
        fan_diameter: false
    }
}
