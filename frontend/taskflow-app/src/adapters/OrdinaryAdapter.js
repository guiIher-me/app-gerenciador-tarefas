import Axios from "axios";

// function axiosInstance(){
//     return Axios.create(initializers);
// }

export function get(url){
    // const axios = axiosInstance();
    return Axios.get(url);
}

export function post(url, data){
    // const axios = axiosInstance();
    return Axios.post(url, data);
}