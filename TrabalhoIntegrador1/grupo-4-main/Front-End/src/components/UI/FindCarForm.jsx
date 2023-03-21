import React from "react";
import "../../styles/find-car-form.css";
import { Form, FormGroup } from "reactstrap";

function FindCarForm() {
  return (
    <Form className="form">
      <div className="d-flex align-items-center justify-content-between flex-wrap">
        <FormGroup className="form_group">
          <input type="text" placeholder="From Address" required />
        </FormGroup>

        <FormGroup className="form_group">
          <input type="text" placeholder="To Address" required />
        </FormGroup>

        <FormGroup className="form_group">
          <input type="date" placeholder="Date" required />
        </FormGroup>

        

        <FormGroup className="form_group">
          <input
            className="journey_time"
            type="time"
            placeholder="Time"
            required
          />
        </FormGroup>

        <FormGroup className="select_group">
          <select>
            <option value="ac">AC Car</option>
            <option value="non-ac">Non AC Car</option>
          </select>
        </FormGroup>


        <FormGroup className="form_group">
          <button className="find__car-btn btn">Find car</button>
        </FormGroup>
      </div>
    </Form>
  );
}

export default FindCarForm;
