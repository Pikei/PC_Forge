import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CpuFilterComponent} from './cpu-filter.component';

describe('CpuFilterComponent', () => {
    let component: CpuFilterComponent;
    let fixture: ComponentFixture<CpuFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [CpuFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(CpuFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
