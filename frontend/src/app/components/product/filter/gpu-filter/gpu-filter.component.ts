import {Component, Input} from '@angular/core';
import {NgIf} from '@angular/common';
import {AbstractProductFilter} from '../AbstractProductFilter';
import {Params} from '../../../../Params';

type Filter = {
    [Params.CHIPSET_PRODUCER]: [],
    [Params.CHIPSET]: [],
    [Params.VRAM_CAPACITY]: [],
    [Params.VRAM_TYPE]: [],
    [Params.DLSS]: [],
    [Params.CONNECTOR]: [],
    [Params.RESOLUTION]: [],
    [Params.PS_POWER]: [],
    [Params.COOLING_TYPE]: [],
    [Params.NUMBER_OF_FANS]: [],
    [Params.LIGHTNING]: [],
    [Params.LENGTH_MINIMUM]: number,
    [Params.LENGTH_MAXIMUM]: number
}

@Component({
    selector: 'app-gpu-filter',
    imports: [
        NgIf
    ],
    templateUrl: './gpu-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class GpuFilterComponent extends AbstractProductFilter {
    @Input() filter!: Filter;

    expandOptions = {
        [Params.CHIPSET_PRODUCER]: false,
        [Params.CHIPSET]: false,
        [Params.VRAM_CAPACITY]: false,
        [Params.VRAM_TYPE]: false,
        [Params.DLSS]: false,
        [Params.CONNECTOR]: false,
        [Params.RESOLUTION]: false,
        [Params.PS_POWER]: false,
        [Params.COOLING_TYPE]: false,
        [Params.NUMBER_OF_FANS]: false
    }

    setSize() {
        const min_length = (document.querySelector("#min_length") as HTMLInputElement)?.value;
        const max_length = (document.querySelector("#max_length") as HTMLInputElement)?.value;
        if (min_length != "") {
            this.selectedFilter.set(Params.LENGTH_MINIMUM, [min_length])
        } else {
            this.selectedFilter.delete(Params.LENGTH_MINIMUM);
        }
        if (max_length != "") {
            this.selectedFilter.set(Params.LENGTH_MAXIMUM, [max_length])
        } else {
            this.selectedFilter.delete(Params.LENGTH_MAXIMUM);
        }
    }

    clearLength() {
        let min_length = (document.querySelector("#min_length") as HTMLInputElement);
        let max_length = (document.querySelector("#max_length") as HTMLInputElement);
        this.selectedFilter.delete(Params.LENGTH_MINIMUM);
        this.selectedFilter.delete(Params.LENGTH_MAXIMUM);
        min_length.value = "";
        max_length.value = "";
    }

    protected readonly Params = Params;
}
