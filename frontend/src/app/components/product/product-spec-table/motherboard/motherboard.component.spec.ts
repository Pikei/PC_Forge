import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MotherboardComponent} from './motherboard.component';

describe('MotherboardComponent', () => {
    let component: MotherboardComponent;
    let fixture: ComponentFixture<MotherboardComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [MotherboardComponent]
        })
            .compileComponents();

        fixture = TestBed.createComponent(MotherboardComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
