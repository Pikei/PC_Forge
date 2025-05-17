import {Component, Inject, Input, OnDestroy, OnInit, PLATFORM_ID} from '@angular/core';
import {isPlatformBrowser, NgClass, NgIf} from '@angular/common';

@Component({
    selector: 'app-slideshow',
    imports: [
        NgIf,
        NgClass,
    ],
    templateUrl: './slideshow.component.html',
    styleUrl: './slideshow.component.scss'
})
export class SlideshowComponent implements OnInit, OnDestroy {
    @Input() images: { src: string, alt: string, href?: string }[] = [];
    index: number = 0;
    private isBrowser: boolean;
    private intervalId: number | undefined;

    constructor(@Inject(PLATFORM_ID) private platformId: object) {
        this.isBrowser = isPlatformBrowser(this.platformId);
    }

    ngOnInit(): void {
        if (this.isBrowser) {
            this.intervalId = window.setInterval(() => this.next(), 5000);
        }
    }

    ngOnDestroy(): void {
        if (this.isBrowser && this.intervalId) {
            window.clearInterval(this.intervalId);
        }
    }

    resetInterval() {
        if (this.isBrowser && this.intervalId) {
            window.clearInterval(this.intervalId);
        }
        this.intervalId = window.setInterval(() => this.next(), 5000);
    }

    next() {
        this.index++;
        if (this.index >= this.images.length) {
            this.index = 0;
        }
        this.resetInterval();
    }

    prev() {
        this.index--;
        if (this.index < 0) {
            this.index = this.images.length - 1;
        }
        this.resetInterval();
    }

    selectImage(index: number) {
        if (index >= 0 && index < this.images.length) {
            this.index = index;
        }
        this.resetInterval();
    }
}
