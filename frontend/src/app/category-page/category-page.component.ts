import {Component} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';

@Component({
    selector: 'app-category-page',
    imports: [HeaderComponent],
    templateUrl: './category-page.component.html',
    styleUrl: './category-page.component.scss'
})
export class CategoryPageComponent {

    goToCategory(category: string) {
        let path: string = "";
        switch (category) {
            case "CPU":
                path = "processor";
                break;
            case "GPU":
                path = "graphics_card";
                break;
            case "RAM":
                path = "memory";
                break;
            case "MB":
                path = "motherboard";
                break;
            case "PS":
                path = "power_supply";
                break;
            case "SSD":
                path = "ssd";
                break;
            case "HDD":
                path = "hdd";
                break;
            case "AIR_COOLER":
                path = "air_cooler";
                break;
            case "LIQUID_COOLER":
                path = "liquid_cooler";
                break;
            case "CASE":
                path = "pc_case";
                break;
            default:
                return;
        }
        window.location.href = "/category/" + path;
    }
}
