import {Component, ElementRef, HostListener, ViewChild} from '@angular/core';
import {TokenService} from '../../token.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  @ViewChild('stickyMenu') menuElement!: ElementRef;

  sticky: boolean = false;

    constructor(private tokenService: TokenService) {
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

    logout() {
        if (!this.tokenService.getToken()) {
            window.location.href = "/login";
        } else if (confirm("Zostaniesz wylogowany")) {
            this.tokenService.removeToken();
            window.location.href = "/home";
        }
    }

    shoppingCart() {
        if (this.tokenService.getToken()) {
            alert("Nie ma jeszcze funkcji koszyka");
        } else {
            window.location.href = "/login";
        }
    }
}
