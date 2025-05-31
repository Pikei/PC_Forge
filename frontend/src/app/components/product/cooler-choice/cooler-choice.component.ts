import {Component, EventEmitter, Output} from '@angular/core';

@Component({
    selector: 'app-cooler-choice',
    imports: [],
    templateUrl: './cooler-choice.component.html',
    styleUrl: './cooler-choice.component.scss'
})
export class CoolerChoiceComponent {
    @Output() chosenCooler = new EventEmitter<string>;

    emitChoice(coolerCategory: string) {
        this.chosenCooler.emit(coolerCategory);
    }
}
