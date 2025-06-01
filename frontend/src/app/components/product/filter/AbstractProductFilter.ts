import {Params} from '../../../Params';

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

    protected checkIfSelected(filterOption: any, value: any): boolean {
        let res = false;
        if (this.selectedFilter.has(value)) {
            if (this.selectedFilter.get(value)?.toString()?.includes(filterOption.toString())) {
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
            this.selectedFilter.set(Params.WIDTH_MINIMUM, [minWidth])
        } else {
            this.selectedFilter.delete(Params.WIDTH_MINIMUM);
        }
        if (maxWidth != "") {
            this.selectedFilter.set(Params.WIDTH_MAXIMUM, [maxWidth])
        } else {
            this.selectedFilter.delete(Params.WIDTH_MAXIMUM);
        }
    }

    protected clearWidth() {
        let minWidth = (document.querySelector("#min_width") as HTMLInputElement);
        let maxWidth = (document.querySelector("#max_width") as HTMLInputElement);
        this.selectedFilter.delete(Params.WIDTH_MINIMUM);
        this.selectedFilter.delete(Params.WIDTH_MAXIMUM);
        minWidth.value = "";
        maxWidth.value = "";
    }

    protected setHeight() {
        const minHeight = (document.querySelector("#min_height") as HTMLInputElement)?.value;
        const maxHeight = (document.querySelector("#max_height") as HTMLInputElement)?.value;
        if (minHeight != "") {
            this.selectedFilter.set(Params.HEIGHT_MINIMUM, [minHeight])
        } else {
            this.selectedFilter.delete(Params.HEIGHT_MINIMUM);
        }
        if (maxHeight != "") {
            this.selectedFilter.set(Params.HEIGHT_MAXIMUM, [maxHeight])
        } else {
            this.selectedFilter.delete(Params.HEIGHT_MAXIMUM);
        }
    }

    protected clearHeight() {
        let minHeight = (document.querySelector("#min_height") as HTMLInputElement);
        let maxHeight = (document.querySelector("#max_height") as HTMLInputElement);
        this.selectedFilter.delete(Params.HEIGHT_MINIMUM);
        this.selectedFilter.delete(Params.HEIGHT_MAXIMUM);
        minHeight.value = "";
        maxHeight.value = "";
    }

    protected setDepth() {
        const minDepth = (document.querySelector("#min_depth") as HTMLInputElement)?.value;
        const maxDepth = (document.querySelector("#max_depth") as HTMLInputElement)?.value;
        if (minDepth != "") {
            this.selectedFilter.set(Params.DEPTH_MINIMUM, [minDepth])
        } else {
            this.selectedFilter.delete(Params.DEPTH_MINIMUM);
        }
        if (maxDepth != "") {
            this.selectedFilter.set(Params.DEPTH_MAXIMUM, [maxDepth])
        } else {
            this.selectedFilter.delete(Params.DEPTH_MAXIMUM);
        }
    }

    protected clearDepth() {
        let minDepth = (document.querySelector("#min_depth") as HTMLInputElement);
        let maxDepth = (document.querySelector("#max_depth") as HTMLInputElement);
        this.selectedFilter.delete(Params.DEPTH_MINIMUM);
        this.selectedFilter.delete(Params.DEPTH_MAXIMUM);
        minDepth.value = "";
        maxDepth.value = "";
    }
}
