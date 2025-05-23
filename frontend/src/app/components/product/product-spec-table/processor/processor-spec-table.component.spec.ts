import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ProcessorSpecTableComponent} from './processor-spec-table.component';

describe('ProcessorSpecTableComponent', () => {
    let component: ProcessorSpecTableComponent;
    let fixture: ComponentFixture<ProcessorSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [ProcessorSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(ProcessorSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
