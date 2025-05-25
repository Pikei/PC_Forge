import {EventEmitter, Output} from '@angular/core';

export abstract class AbstractProductFilter {
    selectedFilter: Map<string, (string | number | boolean)[]> = new Map();

    public getSelectedFilters() {
        return this.selectedFilter;
    }

    public clearFilters() {
        this.selectedFilter.clear();
    }

    public setFilters(filter: any) {
        this.selectedFilter.clear();
        for (const [key, value] of filter) {
            this.selectedFilter.set(key, filter.get(key)[0].split(','));
        }
    }

    protected toggleFilterOption(selectedValue: string | number | boolean, key: string) {
        if (this.selectedFilter.has(key)) {
            let filteredList = this.selectedFilter.get(key) ?? [];
            if (filteredList.includes(selectedValue)) {
                filteredList.splice(filteredList.indexOf(selectedValue), 1);
            } else {
                filteredList.push(selectedValue);
            }
            if (filteredList.length === 0) {
                this.selectedFilter.delete(key);
            } else {
                this.selectedFilter.set(key, filteredList);
            }
        } else {
            this.selectedFilter.set(key, [selectedValue]);
        }
    }

    toggleBooleanOption(filterOptionElement: boolean, key: string) {
        this.selectedFilter.set(key, [filterOptionElement]);
    }

    protected checkIfSelected(filterOption: any, value: string): boolean {
        let res = false;
        if (this.selectedFilter.has(value)) {
            if (this.selectedFilter.get(value)?.includes(filterOption.toString())) {
                res = true;
            }
        }
        return res;
    }

    protected deleteFilters(key: string) {
        if (this.selectedFilter.has(key)) {
            this.selectedFilter.delete(key);
        }
    }

    protected setWidth() {
        const minWidth = (document.querySelector("#min_width") as HTMLInputElement)?.value;
        const maxWidth = (document.querySelector("#max_width") as HTMLInputElement)?.value;
        if (minWidth != "") {
            this.selectedFilter.set('min_width', [minWidth])
        } else {
            this.selectedFilter.delete('min_width');
        }
        if (maxWidth != "") {
            this.selectedFilter.set('max_width', [maxWidth])
        } else {
            this.selectedFilter.delete('max_width');
        }
    }

    protected clearWidth() {
        let minWidth = (document.querySelector("#min_width") as HTMLInputElement);
        let maxWidth = (document.querySelector("#max_width") as HTMLInputElement);
        this.selectedFilter.delete('min_width');
        this.selectedFilter.delete('max_width');
        minWidth.value = "";
        maxWidth.value = "";
    }

    protected setHeight() {
        const minHeight = (document.querySelector("#min_height") as HTMLInputElement)?.value;
        const maxHeight = (document.querySelector("#max_height") as HTMLInputElement)?.value;
        if (minHeight != "") {
            this.selectedFilter.set('min_height', [minHeight])
        } else {
            this.selectedFilter.delete('min_height');
        }
        if (maxHeight != "") {
            this.selectedFilter.set('max_height', [maxHeight])
        } else {
            this.selectedFilter.delete('max_height');
        }
    }

    protected clearHeight() {
        let minHeight = (document.querySelector("#min_height") as HTMLInputElement);
        let maxHeight = (document.querySelector("#max_height") as HTMLInputElement);
        this.selectedFilter.delete('min_height');
        this.selectedFilter.delete('max_height');
        minHeight.value = "";
        maxHeight.value = "";
    }

    protected setDepth() {
        const minDepth = (document.querySelector("#min_depth") as HTMLInputElement)?.value;
        const maxDepth = (document.querySelector("#max_depth") as HTMLInputElement)?.value;
        if (minDepth != "") {
            this.selectedFilter.set('min_depth', [minDepth])
        } else {
            this.selectedFilter.delete('min_depth');
        }
        if (maxDepth != "") {
            this.selectedFilter.set('max_depth', [maxDepth])
        } else {
            this.selectedFilter.delete('max_depth');
        }
    }

    protected clearDepth() {
        let minDepth = (document.querySelector("#min_depth") as HTMLInputElement);
        let maxDepth = (document.querySelector("#max_depth") as HTMLInputElement);
        this.selectedFilter.delete('min_depth');
        this.selectedFilter.delete('max_depth');
        minDepth.value = "";
        maxDepth.value = "";
    }
}
