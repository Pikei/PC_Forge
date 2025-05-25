import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-hdd-filter',
    imports: [
        NgIf
    ],
    templateUrl: './hdd-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class HddFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        format: [],
        interface: [],
        storage: [],
        rotational_speed: []
    }

    expandOptions = {
        format: false,
        interface: false,
        storage: false,
        rotational_speed: false
    }
}
