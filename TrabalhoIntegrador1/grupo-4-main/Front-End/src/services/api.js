import axios from "axios";

// Instância de axios
const api = axios.create({
  // baseURL: "http://localhost:8080/",
  baseURL: "http://54.94.201.9:8080/",
});

export default api;
