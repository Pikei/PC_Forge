import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-spec-table-gpu',
    imports: [],
    templateUrl: './graphics-card-spec-table.component.html',
    styleUrl: './graphics-card-spec-table.component.scss'
})
export class GraphicsCardSpecTableComponent {
    @Input() product!: any;

}
