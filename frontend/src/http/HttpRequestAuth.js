import { HttpRequest } from "./HttpRequest";
import { HttpAuthToken } from "./HttpAuthToken";

class HttpRequestAuth extends HttpRequest {

    request(method, url, data, headers) {
        const token = HttpAuthToken.getToken();
        const authHeaders = { 'Authorization': `Bearer ${token}` };
        headers = {...headers, ...authHeaders};

        return super.request(method, url, data, headers);
    }

}

export { HttpRequestAuth };
