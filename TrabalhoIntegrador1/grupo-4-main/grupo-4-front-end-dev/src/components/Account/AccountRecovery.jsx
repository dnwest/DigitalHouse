import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { Link } from "react-router-dom";

const AccountRecovery = () => {
  return (
    <div
      className="modal show"
      style={{ display: "block", position: "initial" }}
    >
      <Modal.Dialog>
        <Modal.Header>
          <Modal.Title>Find your account</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="formEmailField">
              <Form.Label>
                Please enter your email to search for your account.
              </Form.Label>
              <Form.Control type="email" placeholder="Email" autoFocus />
            </Form.Group>
          </Form>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary">
            <Link style={{"text-decoration": "none", "color": "#d6d5c9"}} to={"/account/login"}>Cancel</Link>
          </Button>

          <Button variant="secondary">Search</Button>
        </Modal.Footer>
      </Modal.Dialog>
    </div>
  );
};

export default AccountRecovery;
