import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LiquidCoolerFilterComponent} from './liquid-cooler-filter.component';

describe('LiquidCoolerFilterComponent', () => {
    let component: LiquidCoolerFilterComponent;
    let fixture: ComponentFixture<LiquidCoolerFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [LiquidCoolerFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(LiquidCoolerFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
