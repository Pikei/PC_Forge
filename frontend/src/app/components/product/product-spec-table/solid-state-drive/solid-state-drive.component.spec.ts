import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SolidStateDriveComponent} from './solid-state-drive.component';

describe('SolidStateDriveComponent', () => {
    let component: SolidStateDriveComponent;
    let fixture: ComponentFixture<SolidStateDriveComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [SolidStateDriveComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(SolidStateDriveComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
