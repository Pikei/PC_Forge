import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GpuFilterComponent} from './gpu-filter.component';

describe('GpuFilterComponent', () => {
    let component: GpuFilterComponent;
    let fixture: ComponentFixture<GpuFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [GpuFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(GpuFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
