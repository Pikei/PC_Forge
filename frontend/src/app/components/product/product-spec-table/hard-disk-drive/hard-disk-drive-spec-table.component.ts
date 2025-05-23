import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-spec-table-hdd',
    imports: [],
    templateUrl: './hard-disk-drive-spec-table.component.html',
    styleUrl: './hard-disk-drive-spec-table.component.scss'
})
export class HardDiskDriveSpecTableComponent {
    @Input() product!: any;

    getDriveStorage() {
        if (this.product.storage > 1000) {
            return Math.round(this.product.storage / 1024) + " TB";
        }
        return this.product.storage + " GB";
    }
}
