import {Component, Input} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";

@Component({
    selector: 'app-power-supply',
    imports: [
        NgIf,
        NgForOf
    ],
    templateUrl: './power-supply.component.html',
    styleUrl: './power-supply.component.scss'
})
export class PowerSupplyComponent {
    @Input() product!: any;
}
