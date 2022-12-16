import axios from "axios";

const api = axios.create({
  baseURL: "https://dhodonto.ctdprojetos.com.br",
  headers: {
    "Content-type": "application/json"
  }
});

export default api;

