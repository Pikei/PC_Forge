import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RequestSender} from '../../request.sender';
import {NgIf} from '@angular/common';

@Component({
    selector: 'app-verify',
    imports: [
        NgIf
    ],
    templateUrl: './verify.component.html',
    styleUrl: './verify.component.scss'
})
export class VerifyComponent {
    private token: string = "";
    success: boolean = false;

    constructor(private route: ActivatedRoute, private sender: RequestSender) {
        this.route.queryParams.subscribe(params => {
            this.token = params['token'];
        })
        this.sender.requestPost('http://localhost:8080/verify?token=' + this.token).subscribe({
            next: () => {
                this.success = true;
            },
            error: () => {
                this.success = false;
            }
        })
    }

    succeeded() {
        return this.success;
    }
}
