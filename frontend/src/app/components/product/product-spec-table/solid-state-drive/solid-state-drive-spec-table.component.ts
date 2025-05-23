import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-spec-table-ssd',
    imports: [],
    templateUrl: './solid-state-drive-spec-table.component.html',
    styleUrl: './solid-state-drive-spec-table.component.scss'
})
export class SolidStateDriveSpecTableComponent {
    @Input() product!: any;

    getDriveStorage() {
        if (this.product.storage > 1000) {
            return Math.round(this.product.storage / 1024) + " TB";
        }
        return this.product.storage + " GB";
    }
}
