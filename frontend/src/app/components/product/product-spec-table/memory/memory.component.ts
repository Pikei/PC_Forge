import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-memory',
    imports: [],
    templateUrl: './memory.component.html',
    styleUrl: './memory.component.scss'
})
export class MemoryComponent {
    @Input() product!: any;

}
