import {Component, Input} from '@angular/core';
import {ProcessorSpecTableComponent} from './processor/processor-spec-table.component';
import {MotherboardSpecTableComponent} from './motherboard/motherboard-spec-table.component';
import {MemorySpecTableComponent} from './memory/memory-spec-table.component';
import {PcCaseSpecTableComponent} from './pc-case/pc-case-spec-table.component';
import {AirCoolerComponentSpecTable} from './air-cooler/air-cooler-spec-table.component';
import {LiquidCoolerSpecTableComponent} from './liquid-cooler/liquid-cooler-spec-table.component';
import {GraphicsCardSpecTableComponent} from './graphics-card/graphics-card-spec-table.component';
import {SolidStateDriveSpecTableComponent} from './solid-state-drive/solid-state-drive-spec-table.component';
import {HardDiskDriveSpecTableComponent} from './hard-disk-drive/hard-disk-drive-spec-table.component';
import {PowerSupplySpecTableComponent} from './power-supply/power-supply-spec-table.component';

@Component({
    selector: 'app-product-spec-table',
    imports: [
        ProcessorSpecTableComponent,
        MotherboardSpecTableComponent,
        MemorySpecTableComponent,
        PcCaseSpecTableComponent,
        AirCoolerComponentSpecTable,
        LiquidCoolerSpecTableComponent,
        GraphicsCardSpecTableComponent,
        SolidStateDriveSpecTableComponent,
        HardDiskDriveSpecTableComponent,
        PowerSupplySpecTableComponent
    ],
    templateUrl: './product-spec-table.component.html',
    styleUrl: './product-spec-table.component.scss'
})
export class ProductSpecTableComponent {
    @Input() product: any;
}
