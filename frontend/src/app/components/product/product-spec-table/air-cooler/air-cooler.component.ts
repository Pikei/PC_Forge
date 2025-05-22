import {Component, Input} from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
    selector: 'app-air-cooler',
    imports: [
        NgForOf
    ],
    templateUrl: './air-cooler.component.html',
    styleUrl: './air-cooler.component.scss'
})
export class AirCoolerComponent {
    @Input() product!: any;
}
