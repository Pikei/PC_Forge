import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LiquidCoolerSpecTableComponent} from './liquid-cooler-spec-table.component';

describe('LiquidCoolerSpecTableComponent', () => {
    let component: LiquidCoolerSpecTableComponent;
    let fixture: ComponentFixture<LiquidCoolerSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [LiquidCoolerSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(LiquidCoolerSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
