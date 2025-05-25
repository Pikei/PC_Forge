import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';
import {Router} from '@angular/router';

@Component({
    selector: 'app-error-page',
    imports: [
        NgStyle
    ],
    templateUrl: './error-page.component.html',
    styleUrl: './error-page.component.scss'
})
export class ErrorPageComponent {

    background = 'error/error_background.png';

    constructor(private router: Router) {
    }

    goToHome() {
        this.router.navigate(['/']);
    }
}
