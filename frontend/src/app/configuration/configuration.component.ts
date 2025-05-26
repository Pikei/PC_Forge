import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HeaderComponent} from '../components/header/header.component';

@Component({
    selector: 'app-configuration',
    imports: [
        HeaderComponent
    ],
    templateUrl: './configuration.component.html',
    styleUrl: './configuration.component.scss'
})
export class ConfigurationComponent {
    configName: string = "";

    constructor(private route: ActivatedRoute) {
        this.configName = this.route.snapshot.paramMap.get('configName')!;
    }
}
