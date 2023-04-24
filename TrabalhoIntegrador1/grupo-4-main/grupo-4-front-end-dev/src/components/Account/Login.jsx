import { useState, useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import LoginModal from "./LoginModal";
import StaticLogin from "./StaticLogin";
import api from "../../services/api";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";

const Login = () => {
  // useLocation retorna o objeto de localização atual
  const location = useLocation();
  // console.log("location: ", location.pathname);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [formData, setFormData] = useState({});

  // useNavigate retorna uma função que permite navegar programaticamente
  const navigate = useNavigate();

  // useContext() retorna um objeto de contexto
  const { loginSession, setLoginSession } = useContext(LoginSessionContext);
  console.log("loginSession in Login: ", loginSession);

  // Função de manipulação do evento onChange
  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  // Função assíncrona para obter o token
  const getAuthorization = async () => {
    try {
      console.log("Credenciais de login: ", formData);
      const response = await api.post("/auth", formData);
      console.log("response.data de getAuthorization(): ", response.data);
      getClientByToken(response.data);
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao entrar no sistema.");
    }
  };

  // Função assíncrona para obter os dados de cadastro do cliente
  const getClientByToken = async (token) => {
    try {
      const configuration = {
        headers: {
          Authorization: token,
        },
      };

      const response = await api.get(
        "/client/registration_data",
        configuration
      );
      console.log("response.data de getClientByToken(): ", response.data);
      // saveUserInSessionStorage(response.data);

      Object.keys(response.data).length !== 0
        ? logIn(token, response.data)
        : alert("Credenciais inválidas.");
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao buscar os dados do usuário.");
    }
  };

  // Função de manipulação do evento onSubmit
  const handleSubmit = (event) => {
    event.preventDefault();
    getAuthorization();
  };

  const logIn = (token, user) => {
    // saveTokenInSessionStorage(token);
    setLoginSession({ isLogged: true, token: token, user: user });
    console.log("loginSession: ", loginSession);
    handleClose();

    if (location.pathname !== "/reservation/step-2") {
      navigate("/home", { replace: true });
    }
  };

  const logOut = () => {
    // setLoginSession({ ...loginSession, isLogged: false });
    setLoginSession({ isLogged: false, token: "", user: "" });
    console.log("isLogged?", loginSession.isLogged);
    // navigate(-1);
  };

  return (
    <>
      {location.pathname === "/account/login" ||
      (location.pathname === "/reservation/step-2" &&
        loginSession.isLogged === false) ? (
        <StaticLogin
          handleClose={handleClose}
          handleChange={handleChange}
          handleSubmit={handleSubmit}
        />
      ) : (
        <LoginModal
          show={show}
          handleShow={handleShow}
          handleClose={handleClose}
          handleChange={handleChange}
          handleSubmit={handleSubmit}
          isLogged={loginSession.isLogged}
          logOut={logOut}
          location={location}
        />
      )}
    </>
  );
};

export default Login;
