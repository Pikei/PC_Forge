import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RequestSender} from '../request.sender';
import * as http from 'node:http';
import {Params} from '../Params';

@Component({
    selector: 'app-payment',
    imports: [],
    templateUrl: './payment.component.html',
    styleUrl: './payment.component.scss'
})
export class PaymentComponent implements OnInit {
    paymentSuccess: boolean = false;

    constructor(private route: ActivatedRoute, private sender: RequestSender) {
    }

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
            this.sender.requestGet(Params.API_URL + "/payment/success?session_id=" + params['session_id']).subscribe()
            this.paymentSuccess = true;
        })
    }

}
