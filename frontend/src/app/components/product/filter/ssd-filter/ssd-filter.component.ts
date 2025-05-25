import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-ssd-filter',
    imports: [
        NgIf
    ],
    templateUrl: './ssd-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class SsdFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        format: [],
        interface: [],
        storage: [],
        read_speed: [],
        write_speed: []
    }

    expandOptions = {
        format: false,
        interface: false,
        storage: false,
        read_speed: false,
        write_speed: false
    }
}
