import React from "react";
import "../../styles/find-car-form.css";
import { Form, FormGroup } from "reactstrap";

function FindCarFormReserve() {
  return (


    <Form className="form">

      <div class="form-reserve">

        <div className="d-flex align-items-center justify-content-between  flex-wrap">
          <FormGroup className="select_group">

          
            <select id="From-address">
              <option value="ac">From Address</option>
              <option value="non-ac">Rio de janeiro</option>
              <option value="non-ac">São Paulo</option>
              <option value="non-ac">Minas Gerais</option>
              <option value="non-ac">Rio Grande do Sul</option>
            </select>

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
          <select id="To-address">
              <option value="ac">To Address</option>
              <option value="non-ac">Rio de janeiro</option>
              <option value="non-ac">São Paulo</option>
              <option value="non-ac">Minas Gerais</option>
              <option value="non-ac">Rio Grande do Sul</option>
            </select>
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

          
          <FormGroup className="form_group">
            <button className="find__car-btn btn">Book now</button>
          </FormGroup>
        </div>
      </div>


    </Form>


  );
}

export default FindCarFormReserve;
