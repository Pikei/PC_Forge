import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SolidStateDriveSpecTableComponent} from './solid-state-drive-spec-table.component';

describe('SolidStateDriveSpecTableComponent', () => {
    let component: SolidStateDriveSpecTableComponent;
    let fixture: ComponentFixture<SolidStateDriveSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [SolidStateDriveSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(SolidStateDriveSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
