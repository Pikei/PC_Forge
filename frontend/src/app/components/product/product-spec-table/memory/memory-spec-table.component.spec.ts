import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MemorySpecTableComponent} from './memory-spec-table.component';

describe('MemorySpecTableComponent', () => {
    let component: MemorySpecTableComponent;
    let fixture: ComponentFixture<MemorySpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [MemorySpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(MemorySpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
