import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AirCoolerFilterComponent} from './air-cooler-filter.component';

describe('AirCoolerFilterComponent', () => {
    let component: AirCoolerFilterComponent;
    let fixture: ComponentFixture<AirCoolerFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [AirCoolerFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(AirCoolerFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
