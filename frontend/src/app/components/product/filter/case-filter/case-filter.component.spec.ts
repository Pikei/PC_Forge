import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CaseFilterComponent} from './case-filter.component';

describe('CaseFilterComponent', () => {
    let component: CaseFilterComponent;
    let fixture: ComponentFixture<CaseFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [CaseFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(CaseFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
