import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';
import {RequestSender} from '../../request.sender';
import {Router} from '@angular/router';

@Component({
    selector: 'app-register',
    imports: [
        NgStyle
    ],
    templateUrl: './register.component.html',
    styleUrl: './register.component.scss'
})
export class RegisterComponent {
    background = 'auth/auth_background.png';

    constructor(private sender: RequestSender, private router: Router) {
    }

    register() {
        const registerBody = {
            "username": (document.querySelector('#login') as HTMLInputElement)?.value,
            "email": (document.querySelector('#email') as HTMLInputElement)?.value,
            "password": (document.querySelector('#password') as HTMLInputElement)?.value,
            "firstName": (document.querySelector('#firstname') as HTMLInputElement)?.value,
            "lastName": (document.querySelector('#lastname') as HTMLInputElement)?.value,
            "phoneNumber": (document.querySelector('#phone') as HTMLInputElement)?.value
        }
        this.sender.requestPost('http://localhost:8080/register', registerBody).subscribe(
            {
                next: () => {
                    alert("Konto utworzone. Sprawdź email, w celu weryfikacji konta")
                    this.router.navigate(['/login']);
                },
                error: err => {
                    if (err.status === 409) {
                        alert(err.error.message)
                    }
                }
            }
        );
    }
}
