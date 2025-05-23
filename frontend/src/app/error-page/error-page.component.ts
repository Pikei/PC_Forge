import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';

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

    goToHome() {
        window.location.href = "/home";
    }
}
