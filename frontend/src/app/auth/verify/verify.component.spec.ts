import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VerifyComponent} from './verify.component';

describe('VerifyComponent', () => {
    let component: VerifyComponent;
    let fixture: ComponentFixture<VerifyComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [VerifyComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(VerifyComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
