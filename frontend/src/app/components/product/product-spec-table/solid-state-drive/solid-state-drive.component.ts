import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-solid-state-drive',
    imports: [],
    templateUrl: './solid-state-drive.component.html',
    styleUrl: './solid-state-drive.component.scss'
})
export class SolidStateDriveComponent {
    @Input() product!: any;

    getDriveStorage() {
        if (this.product.storage > 1000) {
            return Math.round(this.product.storage / 1024) + " TB";
        }
        return this.product.storage + " GB";
    }
}
