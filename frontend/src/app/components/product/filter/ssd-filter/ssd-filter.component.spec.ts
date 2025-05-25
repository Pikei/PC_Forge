import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SsdFilterComponent} from './ssd-filter.component';

describe('SsdFilterComponent', () => {
    let component: SsdFilterComponent;
    let fixture: ComponentFixture<SsdFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [SsdFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(SsdFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
