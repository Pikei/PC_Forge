import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RequestSender} from '../request.sender';
import * as http from 'node:http';

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
            this.sender.requestGet("http://localhost:8080/payment/success?session_id=" + params['session_id']).subscribe()
            this.paymentSuccess = true;
        })
    }

}
