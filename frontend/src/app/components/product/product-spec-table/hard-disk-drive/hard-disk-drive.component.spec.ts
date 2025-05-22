import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HardDiskDriveComponent} from './hard-disk-drive.component';

describe('HardDiskDriveComponent', () => {
    let component: HardDiskDriveComponent;
    let fixture: ComponentFixture<HardDiskDriveComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [HardDiskDriveComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(HardDiskDriveComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
