import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useContext } from "react";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import api from "../../services/api";

const Signup = () => {
  const {
    register,
    handleSubmit,
    getValues,
    formState: { errors },
  } = useForm();

  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  // const [formData, setFormData] = useState({});

  // useContext() retorna um objeto de contexto
  const { loginSession, setLoginSession } = useContext(LoginSessionContext);
  console.log("loginSession in Login: ", loginSession);

  // useNavigate retorna uma função que permite navegar programaticamente
  const navigate = useNavigate();

  // // Função assíncrona para cadastrar um novo cliente
  // const registerClient = async () => {
  //   try {
  //     console.log("Cadastro de novo cliente: ", formData);
  //     const response = await api.post("/client", formData);
  //     console.log("response.data de registerClient(): ", response.data);
  //     setLoginSession({ user: response.data });
  //     getAuthorization();
  //   } catch (error) {
  //     // console.log("error: ", error);
  //     alert("Ocorreu um erro ao cadastrar um novo cliente.");
  //   }
  // };

  // Função assíncrona para obter o token
  const getAuthorization = async (credentials, client) => {
    try {
      console.log("Credenciais de login: ", credentials);
      const response = await api.post("/auth", credentials);
      console.log("response.data de getAuthorization(): ", response.data);
      setLoginSession({ isLogged: true, token: response.data, user: client });
      navigate("/home");
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao entrar no sistema.");
    }
  };

  // Função assíncrona para cadastrar um novo cliente
  const onSubmit = async (data, event) => {
    event.preventDefault();
    try {
      console.log("Cadastro de novo cliente: ", data);
      const response = await api.post("/client", data);
      console.log("response.data de onSubmit(): ", response.data);
      const credentials = {
        username: data.username,
        password: data.password,
      };
      getAuthorization(credentials, response.data);
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao cadastrar um novo cliente.");
    }
  };

  return (
    <div
      className="modal show"
      style={{ display: "block", position: "initial" }}
    >
      <Modal.Dialog>
        <Modal.Header>
          <Modal.Title>Create a new account</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group className="mb-3" controlId="formNameField">
              <Form.Label>Full Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Full Name"
                autoFocus
                {...register("fullName", {
                  required: "Full Name is required",
                })}
              />
              {errors.fullName && (
                <Form.Text className="text-danger">
                  {errors.fullName.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group className="mb-3" controlId="formNationalIdField">
              <Form.Label>National ID</Form.Label>
              <Form.Control
                type="number"
                placeholder="National ID"
                {...register("nationalId", {
                  required: "National ID is required",
                })}
              />
              {errors.nationalId && (
                <Form.Text className="text-danger">
                  {errors.nationalId.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPhoneNumberField">
              <Form.Label>Phone Number</Form.Label>
              <Form.Control
                type="number"
                placeholder="Phone Number"
                {...register("phoneNumber", {
                  required: "Phone Number is required",
                })}
              />
              {errors.phoneNumber && (
                <Form.Text className="text-danger">
                  {errors.phoneNumber.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group
              className="mb-3"
              controlId="formDrivingLicenseNumberField"
            >
              <Form.Label>Driving License Number</Form.Label>
              <Form.Control
                type="number"
                placeholder="Driving License Number"
                {...register("drivingLicenseNumber", {
                  required: "Driving License Number is required",
                })}
              />
              {errors.drivingLicenseNumber && (
                <Form.Text className="text-danger">
                  {errors.drivingLicenseNumber.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group
              className="mb-3"
              controlId="formDrivingLicenseValidityField"
            >
              <Form.Label>Driving License Validity</Form.Label>
              <Form.Control
                type="date"
                placeholder="Driving License Validity"
                {...register("drivingLicenseValidity", {
                  required: "Driving License Validity is required",
                  validate: (value) => {
                    // Verifica se a data informada é maior ou igual a hoje
                    const today = new Date();
                    const selectedDate = new Date(value);
                    return (
                      selectedDate >= today ||
                      "Your driver's license has expired"
                    );
                  },
                })}
              />
              {errors.drivingLicenseValidity && (
                <Form.Text className="text-danger">
                  {errors.drivingLicenseValidity.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBirthdateField">
              <Form.Label>Birthdate</Form.Label>
              <Form.Control
                type="date"
                placeholder="Birthdate"
                {...register("birthdate", {
                  required: "Birthdate is required",
                })}
              />
              {errors.birthdate && (
                <Form.Text className="text-danger">
                  {errors.birthdate.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group className="mb-3" controlId="formEmailField">
              <Form.Label>Email</Form.Label>
              <Form.Control
                type="email"
                placeholder="Email"
                pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                {...register("username", {
                  required: "Email is required | ex: user@email.com",
                  pattern: {
                    value: /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/,
                    message: "Invalid email format | ex: user@email.com",
                  },
                })}
              />
              {errors.email && (
                <Form.Text className="text-danger">
                  {errors.email.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPasswordField">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                {...register("password", {
                  required: "Password is required",
                })}
              />
              {errors.password && (
                <Form.Text className="text-danger">
                  {errors.password.message}
                </Form.Text>
              )}
            </Form.Group>

            <Form.Group className="mb-3" controlId="formConfirmPasswordField">
              <Form.Label>Confirm Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Confirm Password"
                {...register("confirmPassword", {
                  required: "Confirm Password is required",
                  validate: (value) =>
                    value === getValues("password") || "Passwords do not match",
                })}
              />
              {errors.confirmPassword && (
                <Form.Text className="text-danger">
                  {errors.confirmPassword.message}
                </Form.Text>
              )}
            </Form.Group>

            <Button variant="secondary" type="submit">
              Submit
            </Button>
          </Form>
        </Modal.Body>

        <Modal.Footer>
          <Link className="modal_footer_link" to={"/account/login"}>
            Already have an account
          </Link>
        </Modal.Footer>
      </Modal.Dialog>
    </div>
  );
};

export default Signup;
