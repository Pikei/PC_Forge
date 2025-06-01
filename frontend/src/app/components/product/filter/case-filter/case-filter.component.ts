import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';
import {Params} from '../../../../Params';

type Filter = {
    [Params.COLOR]: [],
    [Params.CASE_TYPE]: [],
    [Params.MOTHERBOARD_STANDARD]: [],
    [Params.HEIGHT_MINIMUM]: number,
    [Params.HEIGHT_MAXIMUM]: number,
    [Params.WIDTH_MINIMUM]: number,
    [Params.WIDTH_MAXIMUM]: number,
    [Params.DEPTH_MINIMUM]: number,
    [Params.DEPTH_MAXIMUM]: number,
    [Params.HAS_WINDOW]: [],
    [Params.HAS_POWER_SUPPLY]: [],
    [Params.PS_POWER]: [],
    [Params.MAX_GPU_LENGTH]: [],
    [Params.MAX_CPU_HEIGHT]: [],
    [Params.FRONT_FANS]: [],
    [Params.BACK_FANS]: [],
    [Params.BOTTOM_FANS]: [],
    [Params.TOP_FANS]: [],
    [Params.SIDE_FANS]: [],
    [Params.USB_20]: [],
    [Params.USB_30]: [],
    [Params.USB_31]: [],
    [Params.USB_32]: [],
    [Params.USB_C]: [],
}

@Component({
    selector: 'app-case-filter',
    imports: [
        NgIf
    ],
    templateUrl: './case-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class CaseFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.COLOR]: false,
        [Params.CASE_TYPE]: false,
        [Params.MOTHERBOARD_STANDARD]: false,
        [Params.PS_POWER]: false,
        [Params.MAX_GPU_LENGTH]: false,
        [Params.MAX_CPU_HEIGHT]: false,
        [Params.FRONT_FANS]: false,
        [Params.BACK_FANS]: false,
        [Params.BOTTOM_FANS]: false,
        [Params.TOP_FANS]: false,
        [Params.SIDE_FANS]: false,
        [Params.USB_20]: false,
        [Params.USB_30]: false,
        [Params.USB_31]: false,
        [Params.USB_32]: false,
        [Params.USB_C]: false
    }

    protected readonly Params = Params;
}
