import {Component, Input} from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
    selector: 'app-spec-table-mb',
    imports: [
        NgForOf
    ],
    templateUrl: './motherboard-spec-table.component.html',
    styleUrl: './motherboard-spec-table.component.scss'
})
export class MotherboardSpecTableComponent {
    @Input() product!: any;
}
