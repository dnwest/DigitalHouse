import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { Link } from "react-router-dom";

const StaticLogin = ({ handleClose, handleChange, handleSubmit }) => {
  return (
    <div
      className="modal show"
      style={{ display: "block", position: "initial" }}
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
            <Button variant="secondary" onClick={handleSubmit}>
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
      </Modal.Dialog>
    </div>
  );
};

export default StaticLogin;
