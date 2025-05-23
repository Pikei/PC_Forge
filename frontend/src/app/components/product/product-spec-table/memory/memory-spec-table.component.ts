import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-spec-table-ram',
    imports: [],
    templateUrl: './memory-spec-table.component.html',
    styleUrl: './memory-spec-table.component.scss'
})
export class MemorySpecTableComponent {
    @Input() product!: any;

}
