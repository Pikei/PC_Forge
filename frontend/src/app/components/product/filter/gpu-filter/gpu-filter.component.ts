import {Component, Input} from '@angular/core';
import {NgIf} from '@angular/common';
import {AbstractProductFilter} from '../AbstractProductFilter';

@Component({
    selector: 'app-gpu-filter',
    imports: [
        NgIf
    ],
    templateUrl: './gpu-filter.component.html',
    styleUrl: '../product-filter/product-filter.component.scss'
})
export class GpuFilterComponent extends AbstractProductFilter {
    @Input() filter!: {
        chipset_prod: [],
        chipset: [],
        vram_cap: [],
        ram_type: [],
        dlss: [],
        conn: [],
        res: [],
        power: [],
        cooling_type: [],
        fans: [],
        light: [],
        min_length: number,
        max_length: number
    }

    expandOptions = {
        chipset_prod: false,
        chipset: false,
        vram_cap: false,
        ram_type: false,
        dlss: false,
        conn: false,
        res: false,
        power: false,
        cooling_type: false,
        fans: false
    }

    setSize() {
        const min_length = (document.querySelector("#min_length") as HTMLInputElement)?.value;
        const max_length = (document.querySelector("#max_length") as HTMLInputElement)?.value;
        if (min_length != "") {
            this.selectedFilter.set('min_length', [min_length])
        } else {
            this.selectedFilter.delete('min_length');
        }
        if (max_length != "") {
            this.selectedFilter.set('max_length', [max_length])
        } else {
            this.selectedFilter.delete('max_length');
        }
    }

    clearLength() {
        let min_length = (document.querySelector("#min_length") as HTMLInputElement);
        let max_length = (document.querySelector("#max_length") as HTMLInputElement);
        this.selectedFilter.delete('min_length');
        this.selectedFilter.delete('max_length');
        min_length.value = "";
        max_length.value = "";
    }
}
