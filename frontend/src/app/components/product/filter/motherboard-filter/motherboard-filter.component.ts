import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';
import {Params} from '../../../../Params';

type Filter = {
    [Params.SOCKET]: [],
    [Params.MOTHERBOARD_STANDARD]: [],
    [Params.CHIPSET]: [],
    [Params.RAM_TYPE]: [],
    [Params.NUMBER_OF_MODULES]: [],
    [Params.RAM_CAPACITY]: [],
    [Params.RAM_FREQUENCY]: [],
    [Params.BLUETOOTH]: [],
    [Params.WIFI]: [],
    [Params.WIDTH_MINIMUM]: number,
    [Params.WIDTH_MAXIMUM]: number,
    [Params.DEPTH_MINIMUM]: number,
    [Params.DEPTH_MAXIMUM]: number
}

@Component({
    selector: 'app-motherboard-filter',
    imports: [
        NgIf
    ],
    templateUrl: './motherboard-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class MotherboardFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.SOCKET]: false,
        [Params.MOTHERBOARD_STANDARD]: false,
        [Params.CHIPSET]: false,
        [Params.RAM_TYPE]: false,
        [Params.NUMBER_OF_MODULES]: false,
        [Params.RAM_CAPACITY]: false,
        [Params.RAM_FREQUENCY]: false
    }
    protected readonly Params = Params;
}
