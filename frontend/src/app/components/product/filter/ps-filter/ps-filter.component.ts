import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';
import {Params} from '../../../../Params';

type Filter = {
    [Params.PS_POWER]: [],
    [Params.CERTIFICATE]: [],
    [Params.EFFICIENCY]: [],
    [Params.PROTECTIONS]: [],
    [Params.COOLING_TYPE]: [],
    [Params.FAN_DIAMETER]: [],
    [Params.MODULAR_CABLING]: [],
    [Params.LIGHTNING]: []
}

@Component({
    selector: 'app-ps-filter',
    imports: [
        NgIf
    ],
    templateUrl: './ps-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'

})
export class PsFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.PS_POWER]: false,
        [Params.CERTIFICATE]: false,
        [Params.EFFICIENCY]: false,
        [Params.PROTECTIONS]: false,
        [Params.COOLING_TYPE]: false,
        [Params.FAN_DIAMETER]: false
    }
    protected readonly Params = Params;
}
