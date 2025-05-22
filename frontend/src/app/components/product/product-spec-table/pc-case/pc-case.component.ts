import {Component, Input} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';

@Component({
    selector: 'app-pc-case',
    imports: [
        NgIf,
        NgForOf
    ],
    templateUrl: './pc-case.component.html',
    styleUrl: './pc-case.component.scss'
})
export class PcCaseComponent {
    @Input() product!: any;

}
