import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RequestSender} from '../../service/request.sender';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {Params} from '../../Params';

@Component({
    selector: 'app-reset',
    imports: [
        FormsModule,
        NgIf
    ],
    templateUrl: './reset.component.html',
    styleUrl: './reset.component.scss'
})
export class ResetComponent {
    private token: string = "";
    password = "";
    passwordRepeat = "";

    constructor(private route: ActivatedRoute, private sender: RequestSender, private router: Router) {
        this.route.queryParams.subscribe(params => {
            this.token = params['token'];
            if (this.token == null) {
                this.router.navigate(['/page_not_found']);
            }
        })
    }

    resetPassword() {
        let pass = (document.querySelector("#password") as HTMLInputElement);
        let pass2 = (document.querySelector("#passwordRepeat") as HTMLInputElement);
        this.password = pass.value;
        this.passwordRepeat = pass2.value;
        if (pass.value != pass2.value) {
            pass.value = "";
            pass2.value = "";
        } else if (this.password == this.passwordRepeat && this.password != "") {
            this.sender.requestPost(Params.API_URL + '/password/reset', {
                token: this.token,
                password: this.password
            }).subscribe({
                next: response => {
                    alert("Twoje hasło zostało zresetowane");
                    this.router.navigate(['/login']);
                },
                error: err => {
                    console.log(err)
                    alert(err.error.error)
                }
            })
        }

    }

    invalid() {
        return !(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/.test(this.password)) || this.password != this.passwordRepeat;
    }

    invalidMessage(): string {
        if (this.password != this.passwordRepeat) {
            return "Hasła muszą być takie same"
        }
        if (!(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/.test(this.password))) {
            return "Hasło musi się składać z 8 znaków i zawierać conajmniej jedną małą literę, jedną wielką, cyfrę i znak specjalny"
        }
        return "";
    }
}
