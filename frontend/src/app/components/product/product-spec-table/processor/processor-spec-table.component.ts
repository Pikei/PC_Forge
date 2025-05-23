import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-spec-table-cpu',
    imports: [],
    templateUrl: './processor-spec-table.component.html',
    styleUrl: './processor-spec-table.component.scss'
})
export class ProcessorSpecTableComponent {
    @Input() product!: any;

}
