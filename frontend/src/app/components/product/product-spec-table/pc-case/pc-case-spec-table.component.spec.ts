import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PcCaseSpecTableComponent} from './pc-case-spec-table.component';

describe('PcCaseSpecTableComponent', () => {
    let component: PcCaseSpecTableComponent;
    let fixture: ComponentFixture<PcCaseSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [PcCaseSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(PcCaseSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
