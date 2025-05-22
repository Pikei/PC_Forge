import {Component, Input} from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
    selector: 'app-motherboard',
    imports: [
        NgForOf
    ],
    templateUrl: './motherboard.component.html',
    styleUrl: './motherboard.component.scss'
})
export class MotherboardComponent {
    @Input() product!: any;
}
