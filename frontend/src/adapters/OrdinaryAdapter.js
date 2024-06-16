import Axios from "axios";

// Função para obter o token de autenticação
const getAuthHeaders = () => {
    const token = localStorage.getItem("token");
    return token ? { 'Authorization': `Bearer ${token}` } : {};
};

export function deleteAuthHeaders(){
    localStorage.clear();
    console.log(localStorage.getItem("token"));

}

export function get(url){
    const headers = getAuthHeaders();
    return Axios.get(url, {headers});
}

export function postLoginRegister(url, data){
    return Axios.post(url, data);
}

export function post(url, data){
    const headers = getAuthHeaders();
    return Axios.post(url, data, {headers});
}

export function put(url){
    const headers = getAuthHeaders();
    return Axios.put(url, {}, {headers});
}

export function del(url){
    const headers = getAuthHeaders();
    return Axios.delete(url, {}, {headers});
}