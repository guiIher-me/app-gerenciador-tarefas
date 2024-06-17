import axios from "axios";

class HttpRequest {

    async request(method, url, data, headers) {
        const response = await axios({
            method,
            url,
            data,
            headers,
        });

        return response;
    }

    get(url, headers = {}) {
        return this.request("GET", url, undefined, headers);
    }

    post(url, data = {}, headers = {}) {
        return this.request("POST", url, data, headers);
    }

    put(url, data = {}, headers = {}) {
        return this.request("PUT", url, data, headers);
    }

    delete(url, data = {}, headers = {}) {
        return this.request("DELETE", url, data, headers);
    }

}

export { HttpRequest }
