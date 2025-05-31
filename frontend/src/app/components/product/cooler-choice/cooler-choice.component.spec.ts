import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CoolerChoiceComponent} from './cooler-choice.component';

describe('CoolerChoiceComponent', () => {
    let component: CoolerChoiceComponent;
    let fixture: ComponentFixture<CoolerChoiceComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [CoolerChoiceComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(CoolerChoiceComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
