import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-hard-disk-drive',
    imports: [],
    templateUrl: './hard-disk-drive.component.html',
    styleUrl: './hard-disk-drive.component.scss'
})
export class HardDiskDriveComponent {
    @Input() product!: any;

    getDriveStorage() {
        if (this.product.storage > 1000) {
            return Math.round(this.product.storage / 1024) + " TB";
        }
        return this.product.storage + " GB";
    }
}
