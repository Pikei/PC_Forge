import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {

  @ViewChild('stickyMenu') menuElement!: ElementRef;

  sticky: boolean = false;

  elementPosition: any;

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.elementPosition = this.menuElement.nativeElement.offsetTop;
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
}
