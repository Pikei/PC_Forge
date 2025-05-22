import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AirCoolerComponent} from './air-cooler.component';

describe('AirCoolerComponent', () => {
    let component: AirCoolerComponent;
    let fixture: ComponentFixture<AirCoolerComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [AirCoolerComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(AirCoolerComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
