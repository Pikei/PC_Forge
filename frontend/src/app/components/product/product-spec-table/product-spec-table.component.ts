import {Component, Input} from '@angular/core';
import {ProcessorComponent} from './processor/processor.component';
import {MotherboardComponent} from './motherboard/motherboard.component';
import {MemoryComponent} from './memory/memory.component';
import {PcCaseComponent} from './pc-case/pc-case.component';
import {AirCoolerComponent} from './air-cooler/air-cooler.component';
import {LiquidCoolerComponent} from './liquid-cooler/liquid-cooler.component';
import {GraphicsCardComponent} from './graphics-card/graphics-card.component';
import {SolidStateDriveComponent} from './solid-state-drive/solid-state-drive.component';
import {HardDiskDriveComponent} from './hard-disk-drive/hard-disk-drive.component';
import {PowerSupplyComponent} from './power-supply/power-supply.component';

@Component({
    selector: 'app-product-spec-table',
    imports: [
        ProcessorComponent,
        MotherboardComponent,
        MemoryComponent,
        PcCaseComponent,
        AirCoolerComponent,
        LiquidCoolerComponent,
        GraphicsCardComponent,
        SolidStateDriveComponent,
        HardDiskDriveComponent,
        PowerSupplyComponent
    ],
    templateUrl: './product-spec-table.component.html',
    styleUrl: './product-spec-table.component.scss'
})
export class ProductSpecTableComponent {
    @Input() product: any;
}
