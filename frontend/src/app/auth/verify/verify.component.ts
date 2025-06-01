import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RequestSender} from '../../request.sender';
import {NgIf} from '@angular/common';
import {Params} from '../../Params';

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

    constructor(private route: ActivatedRoute, private router: Router, private sender: RequestSender) {
        this.route.queryParams.subscribe(params => {
            this.token = params['token'];
            if (this.token == null) {
                this.router.navigate(['/page_not_found']);
            }
        })
        this.sender.requestPost(Params.API_URL + '/verify?token=' + this.token).subscribe({
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
