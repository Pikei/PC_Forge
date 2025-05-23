import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})

export class TokenService {
    private TOKEN_KEY = 'JWT_token';

    getToken() {
        return localStorage.getItem(this.TOKEN_KEY);
    }

    setToken(token: string) {
        localStorage.setItem(this.TOKEN_KEY, token);
    }

    removeToken() {
        localStorage.removeItem(this.TOKEN_KEY);
    }
}
