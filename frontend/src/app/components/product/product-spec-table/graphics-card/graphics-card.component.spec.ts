import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GraphicsCardComponent} from './graphics-card.component';

describe('GraphicsCardComponent', () => {
    let component: GraphicsCardComponent;
    let fixture: ComponentFixture<GraphicsCardComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [GraphicsCardComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(GraphicsCardComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
