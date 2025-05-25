import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';
import {TokenService} from '../../service/token.service';
import {RequestSender} from '../../request.sender';
import {Router} from '@angular/router';

@Component({
    selector: 'app-login',
    imports: [
        NgStyle,
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent {
    background = 'auth/auth_background.png';

    constructor(private sender: RequestSender, private tokenService: TokenService, private router: Router) {
    }

    login() {
        const loginBody = {
            "username": (document.querySelector('#login') as HTMLInputElement)?.value,
            "password": (document.querySelector('#password') as HTMLInputElement)?.value
        }
        this.sender.requestPost('http://localhost:8080/login', loginBody).subscribe(
            {
                next: response => {
                    this.handleResponse(response);
                },
                error: () => {
                    alert("Nieprawidłowy login lub hasło")
                }
            });
    }

    private handleResponse(response: any) {
        if (response.body.success === true) {
            this.tokenService.setToken(response.body.jwt);
            this.router.navigate(['/']);
        } else {
            let message: string = "";
            if (response.body.error.includes("USER_NOT_VERIFIED")) {
                message = "Zweryfikuj swój adres email, aby móc się zalogować.";
                if (response.body.error.includes("_NEW_EMAIL_SENT")) {
                    message += "\nWysłano nowy email z linkiem aktywacyjnym."
                }
            }
            alert(message);
        }
    }
}
