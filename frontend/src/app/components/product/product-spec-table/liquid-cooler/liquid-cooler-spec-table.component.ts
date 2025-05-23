import {Component, Input} from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
    selector: 'app-spec-table-liquid-cooler',
    imports: [
        NgForOf
    ],
    templateUrl: './liquid-cooler-spec-table.component.html',
    styleUrl: './liquid-cooler-spec-table.component.scss'
})
export class LiquidCoolerSpecTableComponent {
    @Input() product!: any;

}
