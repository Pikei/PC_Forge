import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PowerSupplySpecTableComponent} from './power-supply-spec-table.component';

describe('PowerSupplySpecTableComponent', () => {
    let component: PowerSupplySpecTableComponent;
    let fixture: ComponentFixture<PowerSupplySpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [PowerSupplySpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(PowerSupplySpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
