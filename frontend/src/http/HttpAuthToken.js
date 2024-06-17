import { UnauthenticatedError } from "../exceptions/UnauthenticatedError";
const TOKEN_LABEL = "token";

class HttpAuthToken {

    static setToken(token) {
        localStorage.setItem(TOKEN_LABEL, token);
    }

    static getToken() {
        const token = localStorage.getItem(TOKEN_LABEL);
        if(!token) throw new UnauthenticatedError();

        return token;
    };

    static deleteToken() {
        localStorage.removeItem(TOKEN_LABEL);
    }

}

export { HttpAuthToken };
