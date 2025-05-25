import {Component, Input} from '@angular/core';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-case-filter',
    imports: [
        NgIf
    ],
    templateUrl: './case-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class CaseFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        color: [],
        case_type: [],
        standard: [],
        min_width: number,
        max_width: number,
        min_depth: number,
        max_depth: number
        min_height: number,
        max_height: number,
        window: [],
        power_supply: [],
        power: [],
        max_gpu_length: [],
        max_cpu_height: [],
        front_fans: [],
        back_fans: [],
        side_fans: [],
        bottom_fans: [],
        top_fans: [],
        usb20: [],
        usb30: [],
        usb31: [],
        usb32: [],
        usbC: [],

    }

    expandOptions = {
        color: false,
        case_type: false,
        standard: false,
        power: false,
        max_gpu_length: false,
        max_cpu_height: false,
        front_fans: false,
        back_fans: false,
        side_fans: false,
        bottom_fans: false,
        top_fans: false,
        usb20: false,
        usb30: false,
        usb31: false,
        usb32: false,
        usbC: false
    }

}
