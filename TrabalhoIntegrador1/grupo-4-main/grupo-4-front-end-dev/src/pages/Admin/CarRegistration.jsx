import Helmet from "../../components/Helmet/Helmet";
import { useContext, useState, useEffect } from "react";
import { Icon } from "@iconify/react";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import { Alert, Button, Col, Container, Form, Row } from "react-bootstrap";
import api from "../../services/api";
import isEmpty from "../../utils/validations";
import "../../styles/car-registration.css";

const CarRegistration = () => {
  const initialState = {
    brand: "",
    model: "",
    color: "",
    licensePlate: "",
    modelYear: "",
    mileage: "",
    categoryId: "",
    locationId: "",
    isAvailable: false,
  };

  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [formData, setFormData] = useState(initialState);
  const [locations, setLocations] = useState([]);
  const [categories, setCategories] = useState([]);
  const [car, setCar] = useState({});
  const [show, setShow] = useState(false);
  // const [isLoading, setIsLoading] = useState(true);

  // useContext() retorna um objeto de contexto
  const { loginSession } = useContext(LoginSessionContext);
  console.log("loginSession in Login: ", loginSession);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
  useEffect(() => {
    // Função assíncrona para buscar todos as categorias e localizações simultaneamente
    const getAllCategoriesAndLocations = () => {
      Promise.all([api.get("/category"), api.get("/location")])
        .then((response) => {
          setCategories(response[0].data);
          setLocations(response[1].data);
        })
        .catch((error) => {
          console.log("error: ", error);
          alert(
            "Ocorreu um erro ao buscar todas as categorias e localizações."
          );
        });
    };

    getAllCategoriesAndLocations();
  }, []);

  // Função assíncrona para cadastrar um carro
  const registerCar = async (token) => {
    try {
      const data = JSON.stringify(formData);

      const configuration = {
        method: "post",
        maxBodyLength: Infinity,
        url: "/car",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
        data: data,
      };

      const response = await api.request(configuration);
      console.log("response.data de registerCar(): ", response.data);
      setCar(response.data);
      setShow(true);
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao cadastrar o carro.");
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    console.log("formData: ", formData);

    if (isEmpty(formData)) {
      alert("Preencha todos os campos!");
      return;
    }

    registerCar(loginSession.token);

    // initialState = formData;
    setFormData(initialState);
    event.target.reset();
  };

  // Carregamento do loading
  if (categories.length === 0) {
    return <div className="loading">Loading&#8230;</div>;
  }

  return (
    <>
      {loginSession.isLogged && loginSession.user.fullName === "Admin" ? (
        <Helmet title="Car Registration">
          {show ? (
            <Alert variant="success" onClose={() => setShow(false)} dismissible>
              Car successfully registered!
            </Alert>
          ) : null}
          <Container className="about_img d-flex  justify-content-center">
            <Form className="car-registration-form" onSubmit={handleSubmit}>
              <h2 className="section_title">
                Car registration <Icon icon="maki:car" />
              </h2>
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
                    defaultValue="Choose a category"
                    name="category"
                    // onChange={handleChange}
                    onChange={(event) =>
                      setFormData({
                        ...formData,
                        categoryId: JSON.parse(event.target.value).id,
                      })
                    }
                  >
                    <option value="">Choose a category</option>
                    {categories.map((category) => (
                      <option
                        key={category.id}
                        value={JSON.stringify(category)}
                      >
                        {category.name}
                      </option>
                    ))}
                  </Form.Select>
                </Form.Group>

                <Form.Group as={Col} controlId="formLocationField">
                  <Form.Label>City</Form.Label>
                  <Form.Select
                    defaultValue="Choose a city"
                    name="location"
                    // onChange={handleChange}
                    onChange={(event) =>
                      setFormData({
                        ...formData,
                        locationId: JSON.parse(event.target.value).id,
                      })
                    }
                  >
                    <option value="">Choose a city</option>
                    {locations.map((location) => (
                      <option
                        key={location.id}
                        value={JSON.stringify(location)}
                      >
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
                  onChange={() =>
                    setFormData({
                      ...formData,
                      isAvailable: !formData.isAvailable,
                    })
                  }
                />
              </Form.Group>

              <Button variant="secondary" type="submit">
                Register
              </Button>
            </Form>
          </Container>
        </Helmet>
      ) : null}
    </>
  );
};

export default CarRegistration;
