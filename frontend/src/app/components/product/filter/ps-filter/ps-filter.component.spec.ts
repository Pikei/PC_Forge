import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PsFilterComponent} from './ps-filter.component';

describe('PsFilterComponent', () => {
    let component: PsFilterComponent;
    let fixture: ComponentFixture<PsFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [PsFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(PsFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
