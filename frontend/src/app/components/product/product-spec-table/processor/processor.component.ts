import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-processor',
    imports: [],
    templateUrl: './processor.component.html',
    styleUrl: './processor.component.scss'
})
export class ProcessorComponent {
    @Input() product!: any;

}
