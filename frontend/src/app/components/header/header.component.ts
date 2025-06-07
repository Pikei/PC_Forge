import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {TokenService} from '../../service/token.service';
import {NgClass, NgIf} from '@angular/common';
import {RequestSender} from '../../request.sender';
import {CartService} from '../../service/shopping-cart.service';
import {ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {Params} from '../../Params';
import {response} from 'express';

@Component({
    selector: 'app-header',
    imports: [
        NgClass,
        NgIf,
        ReactiveFormsModule
    ],
    templateUrl: './header.component.html',
    styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {

    @ViewChild('stickyMenu') menuElement!: ElementRef;
    sticky: boolean = false;
    accountMenuVisible: boolean = false;
    accountInfo = {
        name: "",
        email: "",
        phoneNumber: ""
    };

    category: string = "";

    constructor(private tokenService: TokenService, private sender: RequestSender, protected cartItems: CartService, private router: Router) {
    }

    ngOnInit() {
        const token = this.tokenService.getToken();
        if (token == null) {
            return;
        }
        this.sender.requestGet(Params.API_URL + '/profile').subscribe(
            response => {
                this.accountInfo.name = response.body.firstName + " " + response.body.lastName;
                this.accountInfo.email = response.body.email;
                this.accountInfo.phoneNumber = response.body.phoneNumber;

            });
        this.sender.requestGet(Params.API_URL + '/cart').subscribe(
            response => this.cartItems.setProducts(response.body)
        );
    }

    @HostListener('window:scroll', ['$event'])
    updateStickyState() {
        const windowScroll = window.window.scrollY;
        this.sticky = windowScroll > 100;
        if (this.sticky) {
            document.body.classList.add('simple_header');
        } else {
            document.body.classList.remove('simple_header');
        }
    }

    showAccountMenu() {
        if (!this.tokenService.getToken()) {
            this.router.navigate(['/login']);
        } else {
            this.accountMenuVisible = !this.accountMenuVisible;
        }
    }

    shoppingCart() {
        if (!this.tokenService.getToken()) {
            this.router.navigate(['/login']);
        } else {
            this.router.navigate(['/cart']);
        }
    }

    hideAccountMenu() {
        this.accountMenuVisible = false;
    }

    logout() {
        if (confirm("Czy na pewno chcesz się wylogować?")) {
            this.tokenService.removeToken();
            this.router.navigate(['/home']);
        }
    }

    deleteAccount() {
        if (confirm("Twoje konto zostanie bezpowrotnie usunięte. \nCzy chcesz kontynuować?")) {
            this.sender.requestPost(Params.API_URL + '/delete-account', null).subscribe(
                {
                    next: () => {
                        this.tokenService.removeToken();
                        alert("Twoje konto zostało usunięte")
                        this.router.navigate(['/home']);
                    },
                    error: () => {
                        alert("Wystąpił błąd. Spróbuj ponownie później")
                    }
                }
            )
        }
    }

    setCategory($event: Event) {
        this.category = ($event.target as HTMLInputElement).value;
    }

    searchItems() {
        const searchInput: string = (document.querySelector("#search-input") as HTMLInputElement).value;
        if (searchInput == "" || searchInput == null) {
            if (this.category == "") {
                window.location.href = Params.CLIENT_URL + "/category";
            } else {
                window.location.href = Params.CLIENT_URL + "/category/" + this.category;
            }
        } else {
            if (this.category == "") {
                window.location.href = Params.CLIENT_URL + "/search?name=" + searchInput;
            } else {
                window.location.href = Params.CLIENT_URL + "/category/" + this.category + "?name=" + searchInput;
            }
        }
    }
}
