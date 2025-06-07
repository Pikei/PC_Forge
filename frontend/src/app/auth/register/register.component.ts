import {Component} from '@angular/core';
import {NgStyle} from '@angular/common';
import {RequestSender} from '../../service/request.sender';
import {Router} from '@angular/router';
import {Params} from '../../Params';

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
        let email = (document.querySelector('#email') as HTMLInputElement);
        let login = (document.querySelector('#login') as HTMLInputElement);
        let password = (document.querySelector('#password') as HTMLInputElement);
        let passwordRepeat = (document.querySelector('#password-repeated') as HTMLInputElement);
        let firstName = (document.querySelector('#firstname') as HTMLInputElement);
        let lastName = (document.querySelector('#lastname') as HTMLInputElement);
        let phone = (document.querySelector('#phone') as HTMLInputElement);
        this.resetInputClasses(login, password, passwordRepeat, firstName, lastName, phone);
        this.validateEmail(email);
        this.validateLogin(login);
        this.validatePassword(password, passwordRepeat);
        this.validateFirsAndLastName(firstName, lastName);
        this.validatePhone(phone);

        if (this.allFieldsValid(login, password, passwordRepeat, firstName, lastName, phone)) {
            const registerBody = {
                "username": login.value,
                "email": email.value,
                "password": password.value,
                "firstName": firstName.value,
                "lastName": lastName.value,
                "phoneNumber": phone.value,
            }
            this.sender.requestPost(Params.API_URL + '/register', registerBody).subscribe(
                {
                    next: () => {
                        alert("Konto utworzone. Sprawdź email, w celu weryfikacji konta")
                        this.router.navigate(['/login']);
                    },
                    error: err => {
                        if (err.status === 409) {
                            if (err.error.message.includes("USERNAME_TAKEN")) {
                                login.classList.add('invalid');
                                login.classList.remove('valid');
                                login.value = "";
                                login.placeholder = "Nazwa użytkownika jest już zajęta"
                            } else {
                                login.classList.add('valid');
                                login.classList.remove('invalid');
                            }
                            if (err.error.message.includes("EMAIL_TAKEN")) {
                                email.classList.add('invalid');
                                email.classList.remove('valid');
                                email.value = "";
                                email.placeholder = "Email jest przypisany do innego konta"
                            } else {
                                email.classList.add('valid');
                                email.classList.remove('invalid');
                            }
                        }
                    }
                }
            );
        }
    }

    private validateEmail(email: HTMLInputElement) {
        if (!(/^.+@.+\..+$/.test(email.value)) || email.value.length == 0) {
            email.classList.add('invalid');
            email.classList.remove('valid');
            email.value = "";
            email.placeholder = "Podaj adres prawidłowy adres email";
        }
    }

    private resetInputClasses(login: HTMLInputElement, password: HTMLInputElement, passwordRepeat: HTMLInputElement, firstName: HTMLInputElement, lastName: HTMLInputElement, phone: HTMLInputElement) {
        login.classList.remove('invalid');
        login.classList.remove('valid');
        password.classList.remove('invalid');
        password.classList.remove('valid');
        passwordRepeat.classList.remove('invalid');
        passwordRepeat.classList.remove('valid');
        firstName.classList.remove('invalid');
        firstName.classList.remove('valid');
        lastName.classList.remove('invalid');
        lastName.classList.remove('valid');
        phone.classList.remove('invalid');
        phone.classList.remove('valid');
    }

    private validateLogin(login: HTMLInputElement) {
        if (login.value.length < 3 || login.value.length > 64) {
            login.classList.add('invalid');
            login.classList.remove('valid')
            login.placeholder = "Nieprawidłowa długość"
            login.value = "";
        }
    }

    private validatePassword(password: HTMLInputElement, passwordRepeat: HTMLInputElement) {
        if (password.value != passwordRepeat.value) {
            password.classList.add('invalid');
            password.classList.remove('valid');
            passwordRepeat.classList.add('invalid');
            passwordRepeat.classList.remove('valid');
            password.value = "";
            passwordRepeat.value = "";
            password.placeholder = "Hasła muszą być zgodne";
            passwordRepeat.placeholder = "Hasła muszą być zgodne";
            return;
        }
        if (password.value.length < 8) {
            password.classList.add('invalid');
            password.classList.remove('valid');
            password.value = "";
            password.placeholder = "Hasło musi się składać z 8 znaków";
            passwordRepeat.classList.add('invalid');
            passwordRepeat.classList.remove('valid');
            passwordRepeat.value = "";
            passwordRepeat.placeholder = "Hasło musi się składać z 8 znaków";
            return;
        }
        if (!(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/.test(password.value))) {
            password.classList.add('invalid');
            password.classList.remove('valid');
            password.value = "";
            password.placeholder = "Co najmniej jedna mała i wielka litera, cyfra i znak specjalny";
            passwordRepeat.classList.add('invalid');
            passwordRepeat.classList.remove('valid');
            passwordRepeat.value = "";
            passwordRepeat.placeholder = "Co najmniej jedna mała i wielka litera, cyfra i znak specjalny";
            return;
        }
    }

    private validateFirsAndLastName(firstName: HTMLInputElement, lastName: HTMLInputElement) {
        if (!/^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+(-[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+)?$/.test(firstName.value)) {
            firstName.classList.add('invalid');
            firstName.classList.remove('valid');
            firstName.value = "";
            firstName.placeholder = "Imię powinno zaczynać się wielką literą";
        }

        if (!/^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{3,64}$/.test(lastName.value)) {
            lastName.classList.add('invalid');
            lastName.classList.remove('valid');
            lastName.value = "";
            lastName.placeholder = "Nazwisko powinno zaczynać się wielką literą";
        }
    }

    private validatePhone(phone: HTMLInputElement) {
        if (!(/^\d{9}$/.test(phone.value))) {
            phone.classList.add('invalid');
            phone.classList.remove('valid');
            phone.value = "";
            phone.placeholder = "Numer telefonu powinien zawierać 9 cyfr";
        }
    }

    private allFieldsValid(login: HTMLInputElement, password: HTMLInputElement, passwordRepeat: HTMLInputElement, firstName: HTMLInputElement, lastName: HTMLInputElement, phone: HTMLInputElement) {
        return login.value != "" && password.value != "" && passwordRepeat.value != "" && firstName.value != "" && lastName.value != "" && phone.value != "";
    }

    deleteClass($event: Event) {
        ($event.target as HTMLInputElement).classList.remove('invalid');
        ($event.target as HTMLInputElement).classList.remove('valid');
    }
}
