import {Component, Input} from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
    selector: 'app-liquid-cooler',
    imports: [
        NgForOf
    ],
    templateUrl: './liquid-cooler.component.html',
    styleUrl: './liquid-cooler.component.scss'
})
export class LiquidCoolerComponent {
    @Input() product!: any;

}
