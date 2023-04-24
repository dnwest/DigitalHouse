import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { Navigate, useNavigate } from "react-router-dom";
import { useContext, useState } from "react";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import api from "../../services/api";

const AdminLogin = () => {
  // const [show, setShow] = useState(false);

  // const handleClose = () => setShow(false);
  // const handleShow = () => setShow(true);

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
      Object.keys(response.data).length !== 0
        ? logIn(response.data, { fullName: "Admin" })
        : alert("Credenciais inválidas.");
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao entrar no sistema.");
    }
  };

  // Função de manipulação do evento onSubmit
  const handleSubmit = (event) => {
    event.preventDefault();
    getAuthorization();
  };

  const logIn = (token, user) => {
    setLoginSession({ isLogged: true, token: token, user: user });
    console.log("loginSession: ", loginSession);
    // handleClose();
    navigate("/admin/car_registration");
  };

  return (
    <>
      {loginSession.isLogged && loginSession.user.fullName === "Admin" ? (
        <Navigate to="/admin/car_registration" />
      ) : (
        <div
          className="modal show"
          style={{ display: "block", position: "initial", minHeight: "calc(100vh - 192px)" }}
        >
          <Modal.Dialog>
            <Modal.Header>
              <Modal.Title>Login with your account</Modal.Title>
            </Modal.Header>

            <Modal.Body>
              <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="formEmailField">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Email"
                    name="username"
                    onChange={handleChange}
                    autoFocus
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formPasswordField">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Password"
                    name="password"
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Button variant="secondary" type="submit">
                  Log in
                </Button>
              </Form>
            </Modal.Body>

            {/* <Modal.Footer>
          <Link
            className="modal_footer_link"
            to={"/account/recovery"}
            onClick={handleClose}
          >
            Reset password
          </Link>
          <Link
            className="modal_footer_link"
            to={"/account/signup"}
            onClick={handleClose}
          >
            Create a new account
          </Link>
        </Modal.Footer> */}
          </Modal.Dialog>
        </div>
      )}
    </>
  );
};

export default AdminLogin;
