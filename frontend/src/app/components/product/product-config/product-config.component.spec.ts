import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ProductConfigComponent} from './product-config.component';

describe('ProductConfigComponent', () => {
    let component: ProductConfigComponent;
    let fixture: ComponentFixture<ProductConfigComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [ProductConfigComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(ProductConfigComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
