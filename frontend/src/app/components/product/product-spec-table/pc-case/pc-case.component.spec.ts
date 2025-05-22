import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PcCaseComponent} from './pc-case.component';

describe('PcCaseComponent', () => {
    let component: PcCaseComponent;
    let fixture: ComponentFixture<PcCaseComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [PcCaseComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(PcCaseComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
