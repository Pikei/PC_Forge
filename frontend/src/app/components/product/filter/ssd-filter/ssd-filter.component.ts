import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';
import {Params} from '../../../../Params';

type Filter = {
    [Params.FORMAT]: [],
    [Params.INTERFACE]: [],
    [Params.STORAGE]: [],
    [Params.READ_SPEED]: [],
    [Params.WRITE_SPEED]: []
}

@Component({
    selector: 'app-ssd-filter',
    imports: [
        NgIf
    ],
    templateUrl: './ssd-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class SsdFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.FORMAT]: false,
        [Params.INTERFACE]: false,
        [Params.STORAGE]: false,
        [Params.READ_SPEED]: false,
        [Params.WRITE_SPEED]: false
    }
    protected readonly Params = Params;
}
