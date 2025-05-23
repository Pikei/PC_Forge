import {Component, Input} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-cpu-filter',
    imports: [
        FormsModule,
        NgIf,
    ],
    templateUrl: './cpu-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class CpuFilterComponent {
    @Input() filter!: {
        socket: [],
        model: [],
        num_cores: [],
        freq: [],
        igu: [],
        cooler: [],
        pack: [],
        unlocked: []
    }
    expandOptions = {
        socket: false,
        model: false,
        num_cores: false,
        freq: false,
        igu: false,
        cooler: false,
        pack: false,
        unlocked: false,
    }
}
