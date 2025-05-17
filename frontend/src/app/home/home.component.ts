import {Component} from '@angular/core';
import {HeaderComponent} from '../components/header/header.component';
import {SlideshowComponent} from '../components/slideshow/slideshow.component';

@Component({
  selector: 'app-home',
  imports: [
      HeaderComponent,
      SlideshowComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
    images = [
        {src: 'baner_images/baner1.png', alt: 'Konfigurator komputerowy', href: 'https://example.com/1'},
        {src: 'baner_images/baner2.png', alt: 'Karty  RTX 5000', href: 'https://example.com/2'},
        {src: 'baner_images/baner3.png', alt: 'Procesory intel AMD', href: 'https://example.com/3'},
        {src: 'baner_images/baner4.png', alt: 'Kontakt', href: 'https://example.com/4'},
    ];
}
