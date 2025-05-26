import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';
import {RequestSender} from '../../request.sender';
import {Router} from '@angular/router';

@Component({
    selector: 'app-forgot-password',
    imports: [
        NgStyle
    ],
    templateUrl: './forgot-password.component.html',
    styleUrl: './forgot-password.component.scss'
})
export class ForgotPasswordComponent {
    background = 'auth/auth_background.png';

    constructor(private sender: RequestSender, private router: Router) {
    }

    sendEmail() {
        let email = (document.querySelector('#email') as HTMLInputElement);
        email.classList.remove('invalid');
        this.validateEmail(email);
        if (email.value != "") {
            this.sender.requestPost('http://localhost:8080/password/forgot?email=' + email.value).subscribe(
                {
                    next: () => {
                        alert("Na wskazany adres wysłano link resetujący hasło.")
                        this.router.navigate(['/login']);
                    },
                    error: err => {
                        console.log(err)
                        if (err.status === 400) {
                            email.classList.add('invalid');
                            email.value = "";
                            email.placeholder = "Wskazany adres email nie jest przypisany do żadnego konta."
                        }
                    }
                }
            );
        }
    }

    validateEmail(email: HTMLInputElement) {
        if (!(/^.+@.+\..+$/.test(email.value)) || email.value.length == 0) {
            email.classList.add('invalid');
            email.value = "";
            email.placeholder = "Podaj adres prawidłowy adres email";
        } else {
            email.classList.remove('invalid');
        }
    }

    removeClass($event: Event) {
        ($event.target as HTMLInputElement).classList.remove('invalid');
    }
}
