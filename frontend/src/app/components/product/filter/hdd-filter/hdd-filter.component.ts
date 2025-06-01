import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';
import {Params} from '../../../../Params';

type Filter = {
    [Params.FORMAT]: [],
    [Params.INTERFACE]: [],
    [Params.STORAGE]: [],
    [Params.ROTATIONAL_SPEED]: []
}

@Component({
    selector: 'app-hdd-filter',
    imports: [
        NgIf
    ],
    templateUrl: './hdd-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class HddFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.FORMAT]: false,
        [Params.INTERFACE]: false,
        [Params.STORAGE]: false,
        [Params.ROTATIONAL_SPEED]: false
    }
    protected readonly Params = Params;
}
