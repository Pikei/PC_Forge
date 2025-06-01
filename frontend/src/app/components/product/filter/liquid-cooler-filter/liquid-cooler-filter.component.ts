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
    [Params.COOLER_SIZE]: [],
}

@Component({
    selector: 'app-liquid-cooler-filter',
    imports: [
        NgIf
    ],
    templateUrl: './liquid-cooler-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class LiquidCoolerFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.SOCKET]: false,
        [Params.NUMBER_OF_FANS]: false,
        [Params.FAN_DIAMETER]: false,
        [Params.NOISE_LEVEL]: false,
        [Params.COOLER_SIZE]: false
    }
    protected readonly Params = Params;
}
