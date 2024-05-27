import Axios from "axios";
import Config from '../config.json';

// Configurar uma instância do Axios
const axiosInstance = Axios.create({
    baseURL: Config.apiURL,
    headers: {
        'Content-Type': 'application/json'
    }
});

// Função para obter o token de autenticação
const getAuthHeaders = () => {
    const token = localStorage.getItem("token");
    //console.log('***TOKEN***:  '+token);
    return token ? { 'Authorization': `Bearer ${token}` } : {};
};

export function get(url){
    // const axios = axiosInstance();
    const headers = getAuthHeaders();
    //console.log('***HEADERS***:  '+headers.Authorization);
    return Axios.get(url, {headers});
}

export function post(url, data){
    // const axios = axiosInstance();
    //const headers = getAuthHeaders();
    return Axios.post(url, data/*,{headers}*/);
}