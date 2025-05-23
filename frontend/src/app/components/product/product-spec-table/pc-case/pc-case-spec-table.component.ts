import {Component, Input} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';

@Component({
    selector: 'app-spec-table-case',
    imports: [
        NgIf,
        NgForOf
    ],
    templateUrl: './pc-case-spec-table.component.html',
    styleUrl: './pc-case-spec-table.component.scss'
})
export class PcCaseSpecTableComponent {
    @Input() product!: any;

}
