import {Component, Input} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";

@Component({
    selector: 'app-spec-table-power-supply',
    imports: [
        NgIf,
        NgForOf
    ],
    templateUrl: './power-supply-spec-table.component.html',
    styleUrl: './power-supply-spec-table.component.scss'
})
export class PowerSupplySpecTableComponent {
    @Input() product!: any;
}
