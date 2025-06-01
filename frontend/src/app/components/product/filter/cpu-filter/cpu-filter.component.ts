import {Component, Input} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {Params} from '../../../../Params';

type Filter = {
    [Params.SOCKET]: [],
    [Params.MODEL]: [],
    [Params.CPU_FREQUENCY]: [],
    [Params.NUMBER_OF_CORES]: [],
    [Params.INTEGRATED_GRAPHICS_UNIT]: [],
    [Params.COOLER_INCLUDED]: [],
    [Params.PACKAGING_TYPE]: [],
    [Params.CORE_UNLOCKED]: [],
}

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
    @Input() filter!: Filter;

    expandOptions = {
        [Params.SOCKET]: false,
        [Params.MODEL]: false,
        [Params.CPU_FREQUENCY]: false,
        [Params.NUMBER_OF_CORES]: false,
        [Params.INTEGRATED_GRAPHICS_UNIT]: false,
        [Params.PACKAGING_TYPE]: false
    }

    toggleCpuModel(cpuModel: string) {
        cpuModel = cpuModel.substring(cpuModel.indexOf(' ') + 1);
        this.toggleFilterOption(cpuModel, Params.MODEL)
    }

    modelSelected(cpuModel: string) {
        cpuModel = cpuModel.substring(cpuModel.indexOf(' ') + 1);
        let res = false;
        if (this.selectedFilter.has(Params.MODEL)) {
            if (this.selectedFilter.get(Params.MODEL)?.includes(cpuModel)) {
                res = true;
            }
        }
        return res;
    }

    protected readonly Params = Params;
}
