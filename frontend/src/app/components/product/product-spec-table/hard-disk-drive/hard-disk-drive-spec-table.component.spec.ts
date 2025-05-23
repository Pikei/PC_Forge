import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HardDiskDriveSpecTableComponent} from './hard-disk-drive-spec-table.component';

describe('HardDiskDriveSpecTableComponent', () => {
    let component: HardDiskDriveSpecTableComponent;
    let fixture: ComponentFixture<HardDiskDriveSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [HardDiskDriveSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(HardDiskDriveSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
