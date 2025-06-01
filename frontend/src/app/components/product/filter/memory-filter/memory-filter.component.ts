import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';
import {Params} from '../../../../Params';

type Filter = {
    [Params.RAM_TYPE]: [],
    [Params.RAM_CAPACITY]: [],
    [Params.RAM_FREQUENCY]: [],
    [Params.NUMBER_OF_MODULES]: [],
    [Params.LATENCY]: [],
    [Params.LIGHTNING]: []
}

@Component({
    selector: 'app-memory-filter',
    imports: [
        NgIf
    ],
    templateUrl: './memory-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class MemoryFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.RAM_TYPE]: false,
        [Params.RAM_CAPACITY]: false,
        [Params.RAM_FREQUENCY]: false,
        [Params.NUMBER_OF_MODULES]: false,
        [Params.LATENCY]: false
    }
    protected readonly Params = Params;
}
