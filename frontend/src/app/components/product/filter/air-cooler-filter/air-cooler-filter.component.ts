import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';
import {Params} from '../../../../Params';

type Filter = {
    [Params.SOCKET]: [],
    [Params.NUMBER_OF_FANS]: [],
    [Params.FAN_DIAMETER]: [],
    [Params.NOISE_LEVEL]: [],
    [Params.LIGHTNING]: [],
    [Params.BASE_MATERIAL]: [],
    [Params.HEIGHT_MINIMUM]: number,
    [Params.HEIGHT_MAXIMUM]: number,
    [Params.WIDTH_MINIMUM]: number,
    [Params.WIDTH_MAXIMUM]: number,
    [Params.DEPTH_MINIMUM]: number,
    [Params.DEPTH_MAXIMUM]: number
    [Params.VERTICAL_INSTALLATION]: [],
}

@Component({
    selector: 'app-air-cooler-filter',
    imports: [
        NgIf
    ],
    templateUrl: './air-cooler-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class AirCoolerFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.SOCKET]: false,
        [Params.NUMBER_OF_FANS]: false,
        [Params.FAN_DIAMETER]: false,
        [Params.NOISE_LEVEL]: false,
        [Params.BASE_MATERIAL]: false
    };
    protected readonly Params = Params;
}
