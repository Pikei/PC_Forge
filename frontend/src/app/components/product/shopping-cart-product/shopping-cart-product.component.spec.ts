import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ShoppingCartProductComponent} from './shopping-cart-product.component';

describe('ShoppingCartProductComponent', () => {
    let component: ShoppingCartProductComponent;
    let fixture: ComponentFixture<ShoppingCartProductComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [ShoppingCartProductComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(ShoppingCartProductComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
