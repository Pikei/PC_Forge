import {TokenService} from './token.service';
import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class RequestSender {
    constructor(private tokenService: TokenService) {
    }

    private http = inject(HttpClient);

    requestGet(url: string): Observable<any> {
        const token = this.tokenService.getToken();
        if (token) {
            return this.http.get(url, {headers: {Authorization: `Bearer ${token}`}, observe: 'response'});
        }
        return this.http.get(url, {observe: 'response'});
    }

    requestPost(url: string, requestBody?: any): Observable<any> {
        requestBody = (requestBody === undefined) ? null : requestBody;
        const token = this.tokenService.getToken();
        if (token) {
            return this.http.post(url, requestBody, {headers: {Authorization: `Bearer ${token}`}, observe: 'response'});
        } else {
            return this.http.post<any>(url, requestBody, {observe: 'response'});
        }
    }
}
