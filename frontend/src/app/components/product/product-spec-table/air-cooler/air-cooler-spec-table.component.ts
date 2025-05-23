import {Component, Input} from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
    selector: 'app-spec-table-air-cooler',
    imports: [
        NgForOf
    ],
    templateUrl: './air-cooler-spec-table.component.html',
    styleUrl: './air-cooler-spec-table.component.scss'
})
export class AirCoolerComponentSpecTable {
    @Input() product!: any;
}
