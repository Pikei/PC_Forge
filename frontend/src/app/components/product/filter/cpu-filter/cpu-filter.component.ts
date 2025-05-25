import {Component, Input} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {AbstractProductFilter} from '../AbstractProductFilter';

@Component({
    selector: 'app-cpu-filter',
    imports: [
        FormsModule,
        NgIf,
    ],
    templateUrl: './cpu-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class CpuFilterComponent extends AbstractProductFilter {
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

    toggleCpuModel(cpuModel: string) {
        cpuModel = cpuModel.substring(cpuModel.indexOf(' ') + 1);
        this.toggleFilterOption(cpuModel, 'model')
    }

    modelSelected(cpuModel: string) {
        cpuModel = cpuModel.substring(cpuModel.indexOf(' ') + 1);
        let res = false;
        if (this.selectedFilter.has('model')) {
            if (this.selectedFilter.get('model')?.includes(cpuModel)) {
                res = true;
            }
        }
        return res;
    }
}
