import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-graphics-card',
    imports: [],
    templateUrl: './graphics-card.component.html',
    styleUrl: './graphics-card.component.scss'
})
export class GraphicsCardComponent {
    @Input() product!: any;

}
