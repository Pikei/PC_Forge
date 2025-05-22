import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ProductSpecTableComponent} from './product-spec-table.component';

describe('ProductSpecTableComponent', () => {
    let component: ProductSpecTableComponent;
    let fixture: ComponentFixture<ProductSpecTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [ProductSpecTableComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(ProductSpecTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
