import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HddFilterComponent} from './hdd-filter.component';

describe('HddFilterComponent', () => {
    let component: HddFilterComponent;
    let fixture: ComponentFixture<HddFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [HddFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(HddFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
