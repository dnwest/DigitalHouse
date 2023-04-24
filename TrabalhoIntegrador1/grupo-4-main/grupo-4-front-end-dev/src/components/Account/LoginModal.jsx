import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { Link } from "react-router-dom";
import "../../styles/login.css";
import { Icon } from "@iconify/react";

const LoginModal = ({
  show,
  handleShow,
  handleClose,
  handleChange,
  handleSubmit,
  isLogged,
  logOut,
}) => {
  const renderButton = () => {
    console.log("isLogged in renderButton", isLogged);
    if (isLogged) {
      return (
        // <Button variant="secondary" onClick={logOut}>
        //   Log out
        // </Button>
        <Link className="d-flex align-items-center gap-1" onClick={logOut} to={"/home"}>
          <Icon icon="ri-user-line" className="header_top_right_icon" /> Log out
        </Link>
      );
    } else {
      return (
        // <Button variant="secondary" onClick={handleShow}>
        //   Log in
        // </Button>
        <Link className="d-flex align-items-center gap-1" onClick={handleShow}>
          <Icon icon="ri-user-line" className="header_top_right_icon" /> Log in
        </Link>
      );
    }
  };
 
  return (
    <>
      {renderButton()}

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
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
        <Modal.Footer>
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
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default LoginModal;
