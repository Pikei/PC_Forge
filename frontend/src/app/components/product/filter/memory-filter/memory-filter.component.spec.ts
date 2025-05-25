import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MemoryFilterComponent} from './memory-filter.component';

describe('MemoryFilterComponent', () => {
    let component: MemoryFilterComponent;
    let fixture: ComponentFixture<MemoryFilterComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [MemoryFilterComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(MemoryFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
