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
        {src: 'baner_images/baner1.png', alt: 'Konfigurator komputerowy', href: 'config/new'},
        {
            src: 'baner_images/baner2.png',
            alt: 'Karty  RTX 5000',
            href: 'category/graphics_card?chipset=GeForce%20RTX%205090,GeForce%20RTX%205080,GeForce%20RTX%205070%20Ti,GeForce%20RTX%205070'
        },
        {src: 'baner_images/baner3.png', alt: 'Procesory intel AMD', href: 'category/processor?producer=AMD,Intel'}
    ];
}
