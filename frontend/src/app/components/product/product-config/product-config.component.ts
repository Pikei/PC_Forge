import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgIf} from "@angular/common";
import {ProductCardComponent} from "../product-card/product-card.component";

@Component({
    selector: 'app-product-config',
    imports: [
        NgIf,
        ProductCardComponent
    ],
    templateUrl: './product-config.component.html',
    styleUrl: './product-config.component.scss'
})
export class ProductConfigComponent {
    @Input() product!: any;
    @Input() categoryCode: string = "";
    @Output() remove = new EventEmitter<string>();
    @Output() edit = new EventEmitter<string>();

    removeProduct() {
        this.remove.emit(this.product.category);
    }

    getProductIconSrc(): string {
        const iconsPath = "icons/config/";
        switch (this.categoryCode) {
            case "CPU":
                return iconsPath + "cpu.png";
            case "GPU":
                return iconsPath + "gpu.png";
            case "RAM":
                return iconsPath + "ram.png";
            case "MB":
                return iconsPath + "mb.png";
            case "PS":
                return iconsPath + "ps.png";
            case "SSD":
                return iconsPath + "ssd.png";
            case "HDD":
                return iconsPath + "hdd.png";
            case "AIR_COOLER":
                return iconsPath + "air_cooler.png";
            case "LIQUID_COOLER":
                return iconsPath + "lq_cooler.png";
            case "CASE":
                return iconsPath + "case.png";
        }
        return ""
    }

    changeProduct() {
        this.edit.emit(this.categoryCode);
    }
}
