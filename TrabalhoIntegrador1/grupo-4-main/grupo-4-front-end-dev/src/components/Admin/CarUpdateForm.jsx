import Helmet from "../../components/Helmet/Helmet";
import { useContext, useState, useEffect } from "react";
import { Icon } from "@iconify/react";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import {
  Alert,
  Button,
  Col,
  Container,
  Form,
  Modal,
  Row,
} from "react-bootstrap";
import api from "../../services/api";
import isEmpty from "../../utils/validations";
import "../../styles/car-registration.css";

const CarUpdateForm = ({
  categories,
  locations,
  cars,
  car,
  carIndex,
  category,
  location,
  token,
  setCars,
}) => {
  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [formData, setFormData] = useState({
    ...car,
    categoryId: category.id,
    locationId: location.id,
  });

  console.log("Category: ", category);
  console.log("Location: ", location);
  console.log("formData: ", formData);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  // Função assíncrona para atualizar um carro
  const updateCar = async (token) => {
    try {
      const data = JSON.stringify(formData);

      const configuration = {
        method: "put",
        maxBodyLength: Infinity,
        url: "/car",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
        data: data,
      };

      const response = await api.request(configuration);
      console.log("response.data de updateCar(): ", response.data);
      const updatedCars = [...cars];
      updatedCars[carIndex] = response.data;
      setCars(updatedCars);
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao atualizar o carro.");
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = () => {
    console.log("formData: ", formData);

    if (isEmpty(formData)) {
      alert("Preencha todos os campos!");
      return;
    }

    updateCar(token);
    handleClose();

    // initialState = formData;
    // setFormData(car);
    // event.target.reset();
  };

  return (
    <>
      <Button variant="secondary" onClick={handleShow}>
        Edit
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Car Update</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            {/* <h2 className="section_title">
              Car registration <Icon icon="maki:car" />
            </h2> */}
            <Row className="mb-3">
              <Form.Group as={Col} controlId="formBrandField">
                <Form.Label>Brand</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Brand"
                  name="brand"
                  value={formData.brand}
                  onChange={handleChange}
                  autoFocus
                  required
                />
              </Form.Group>

              <Form.Group as={Col} controlId="formModelField">
                <Form.Label>Model</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Model"
                  name="model"
                  value={formData.model}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Row>

            <Row className="mb-3">
              <Form.Group as={Col} controlId="formColorField">
                <Form.Label>Color</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Color"
                  name="color"
                  value={formData.color}
                  onChange={handleChange}
                  required
                />
              </Form.Group>

              <Form.Group as={Col} controlId="formLicensePlateField">
                <Form.Label>License Plate</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="License Plate"
                  name="licensePlate"
                  value={formData.licensePlate}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Row>

            <Row className="mb-3">
              <Form.Group as={Col} controlId="formModelYearField">
                <Form.Label>Model Year</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="Model Year"
                  name="modelYear"
                  value={formData.modelYear}
                  onChange={handleChange}
                  required
                />
              </Form.Group>

              <Form.Group as={Col} controlId="formMileageField">
                <Form.Label>Mileage</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="Mileage"
                  name="mileage"
                  value={formData.mileage}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Row>

            <Row className="mb-3">
              <Form.Group as={Col} controlId="formCategoryField">
                <Form.Label>Category</Form.Label>
                <Form.Select
                  defaultValue={JSON.stringify(category)}
                  name="categoryId"
                  // onChange={handleChange}
                  onChange={(event) =>
                    setFormData({
                      ...formData,
                      categoryId: JSON.parse(event.target.value).id,
                    })
                  }
                >
                  <option value={JSON.stringify(category)}>
                    {category.name}
                  </option>
                  {categories.map((category) => (
                    <option key={category.id} value={JSON.stringify(category)}>
                      {category.name}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Form.Group as={Col} controlId="formLocationField">
                <Form.Label>City</Form.Label>
                <Form.Select
                  defaultValue={JSON.stringify(location)}
                  name="locationId"
                  // onChange={handleChange}
                  onChange={(event) =>
                    setFormData({
                      ...formData,
                      locationId: JSON.parse(event.target.value).id,
                    })
                  }
                >
                  <option value={JSON.stringify(location)}>
                    {location.city}
                  </option>
                  {locations.map((location) => (
                    <option key={location.id} value={JSON.stringify(location)}>
                      {location.city}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>
            </Row>

            <Form.Group className="mb-3" id="formIsAvailableField">
              <Form.Check
                type="checkbox"
                label="Is available"
                name="isAvailable"
                value={formData.isAvailable}
                checked={formData.isAvailable}
                onChange={() =>
                  setFormData({
                    ...formData,
                    isAvailable: !formData.isAvailable,
                  })
                }
              />
            </Form.Group>

            {/* <Button variant="secondary" type="submit">
              Register
            </Button> */}
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="secondary" onClick={handleSubmit}>
            Update
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default CarUpdateForm;
