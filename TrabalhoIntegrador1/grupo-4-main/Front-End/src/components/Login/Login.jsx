import React from "react";
import { Link } from "react-router-dom";
import { Modal, ModalFooter, ModalHeader, ModalBody } from "reactstrap";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";
import "../../styles/login.css";

const Login = () => {
  // Modal open state
  const [modal, setModal] = React.useState(false);

  // Toggle for Modal
  const toggle = () => setModal(!modal);

  return (
    <div>
      <Link className="d-flex align-items-center gap-1" onClick={toggle}>
        <i class="ri-user-line"></i> Login
      </Link>
      <Modal isOpen={modal} toggle={toggle}>
        <ModalHeader toggle={toggle}>Please Login</ModalHeader>
        <ModalBody>
          <Form>
            <FormGroup>
              <Label for="email">Email</Label>
              <Input type="email" id="email" placeholder="Enter email" />
            </FormGroup>

            <FormGroup>
              <Label for="password">Password</Label>
              <Input type="password" id="password" placeholder="Password" />
            </FormGroup>

            <Button className="btn" type="submit">
              Submit
            </Button>
          </Form>
        </ModalBody>
        <ModalFooter className="gap-2">
          <Link className="modal_footer_link">Not a client yet?</Link>
          <Link className="modal_footer_link">Reset Password</Link>
          <Link className="modal_footer_link" onClick={toggle}>
            Close
          </Link>
        </ModalFooter>
      </Modal>
    </div>
  );
};
export default Login;
