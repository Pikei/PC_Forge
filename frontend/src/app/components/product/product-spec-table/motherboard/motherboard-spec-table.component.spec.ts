import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MotherboardSpecTableComponent} from './motherboard-spec-table.component';

describe('MotherboardSpecTableComponent', () => {
    let component: MotherboardSpecTableComponent;
    let fixture: ComponentFixture<MotherboardSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [MotherboardSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(MotherboardSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
