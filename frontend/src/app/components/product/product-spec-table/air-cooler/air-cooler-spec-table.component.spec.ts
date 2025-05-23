import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AirCoolerComponentSpecTable} from './air-cooler-spec-table.component';

describe('AirCoolerComponent', () => {
    let component: AirCoolerComponentSpecTable;
    let fixture: ComponentFixture<AirCoolerComponentSpecTable>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [AirCoolerComponentSpecTable]
        })
            .compileComponents();

        fixture = TestBed.createComponent(AirCoolerComponentSpecTable);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
