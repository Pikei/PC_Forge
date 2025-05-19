import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {TokenService} from '../../token.service';
import {NgClass, NgIf} from '@angular/common';
import {RequestSender} from '../../request.sender';

@Component({
    selector: 'app-header',
    imports: [
        NgClass,
        NgIf
    ],
    templateUrl: './header.component.html',
    styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {

    @ViewChild('stickyMenu') menuElement!: ElementRef;

    sticky: boolean = false;
    accountMenuVisible: boolean = false;
    cartItems: number = 0;
    accountInfo = {
        name: "",
        email: "",
        phoneNumber: ""
    };

    constructor(private tokenService: TokenService, private sender: RequestSender) {
    }

    ngOnInit() {
        const token = this.tokenService.getToken();
        if (token == null) {
            return;
        }
        this.sender.requestGet('http://localhost:8080/profile').subscribe(
            {
                next: response => {
                    this.accountInfo.name = response.body.firstName + " " + response.body.lastName;
                    this.accountInfo.email = response.body.email;
                    this.accountInfo.phoneNumber = response.body.phoneNumber;
                },
                error: err => {
                    console.log(err)
                }
            });
        this.sender.requestGet('http://localhost:8080/cart').subscribe(
            {
                next: response => {
                    for (let i = 0; i < response.body.length; i++) {
                        this.cartItems += response.body[i].productQuantity;
                    }
                },
                error: err => {
                    console.log(err)
                }
            });
    }

    @HostListener('window:scroll', ['$event'])
    updateStickyState() {
        const windowScroll = window.window.scrollY;
        this.sticky = windowScroll >= 50;
        if (this.sticky) {
            document.body.classList.add('full-height-header');
        } else {
            document.body.classList.remove('full-height-header');
        }
    }

    showAccountMenu() {
        if (!this.tokenService.getToken()) {
            window.location.href = "/login";
        } else {
            this.accountMenuVisible = !this.accountMenuVisible;
        }
    }

    shoppingCart() {
        if (!this.tokenService.getToken()) {
            window.location.href = "/login";
        } else {
            window.location.href = "/cart";
        }
    }

    hideAccountMenu() {
        this.accountMenuVisible = false;
    }

    logout() {
        if (confirm("Czy na pewno chcesz się wylogować?")) {
            this.tokenService.removeToken();
            window.location.href = "/home";
        }
    }

    deleteAccount() {
        if (confirm("Twoje konto zostanie bezpowrotnie usunięte. \nCzy chcesz kontynuować?")) {
            this.sender.requestPost('http://localhost:8080/delete-account', null).subscribe(
                {
                    next: () => {
                        this.tokenService.removeToken();
                        alert("Twoje konto zostało usunięte")
                        window.location.href = "/home";
                    },
                    error: () => {
                        alert("Wystąpił błąd. Spróbuj ponownie później")
                    }
                }
            )
        }
    }
}
