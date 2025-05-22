import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LiquidCoolerComponent} from './liquid-cooler.component';

describe('LiquidCoolerComponent', () => {
    let component: LiquidCoolerComponent;
    let fixture: ComponentFixture<LiquidCoolerComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [LiquidCoolerComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(LiquidCoolerComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
