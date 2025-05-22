import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PowerSupplyComponent} from './power-supply.component';

describe('PowerSupplyComponent', () => {
    let component: PowerSupplyComponent;
    let fixture: ComponentFixture<PowerSupplyComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [PowerSupplyComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(PowerSupplyComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
