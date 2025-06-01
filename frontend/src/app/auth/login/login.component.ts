import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';
import {TokenService} from '../../service/token.service';
import {RequestSender} from '../../request.sender';
import {Router} from '@angular/router';
import {Params} from '../../Params';

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
        let login = (document.querySelector('#login') as HTMLInputElement);
        let password = (document.querySelector('#password') as HTMLInputElement);
        if (login.value == "") {
            login.classList.add('invalid');
            login.placeholder = "Podaj login"
        }
        if (password.value == "") {
            password.classList.add('invalid');
            password.placeholder = "Podaj hasło"
        }

        if (login.value == "" || password.value == "") {
            return;
        }

        const loginBody = {
            "username": login.value,
            "password": password.value
        }
        this.sender.requestPost(Params.API_URL + '/login', loginBody).subscribe(
            {
                next: response => {
                    this.handleResponse(response);
                },
                error: () => {
                    login.value = "";
                    login.classList.add('invalid');
                    password.value = "";
                    password.classList.add('invalid');
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

    removeClass($event: Event) {
        ($event.target as HTMLInputElement).classList.remove('invalid');
    }
}
