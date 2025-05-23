import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GraphicsCardSpecTableComponent} from './graphics-card-spec-table.component';

describe('GraphicsCardSpecTableComponent', () => {
    let component: GraphicsCardSpecTableComponent;
    let fixture: ComponentFixture<GraphicsCardSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [GraphicsCardSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(GraphicsCardSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
