import Helmet from "../../components/Helmet/Helmet";
import { useContext, useState, useEffect } from "react";
import { Icon } from "@iconify/react";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import { Alert, Button, Col, Container, Form, Row } from "react-bootstrap";
import api from "../../services/api";
import isEmpty from "../../utils/validations";
import "../../styles/car-update.css";
import Table from "react-bootstrap/Table";
import CarUpdateForm from "../../components/Admin/CarUpdateForm";

const CarUpdate = () => {
  const initialState = {
    category: {},
    location: {},
  };

  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [formData, setFormData] = useState(initialState);
  const [locations, setLocations] = useState([]);
  const [categories, setCategories] = useState([]);
  const [cars, setCars] = useState([]);
  const [car, setCar] = useState({});
  const [show, setShow] = useState(false);
  // const [isLoading, setIsLoading] = useState(true);
  console.log("car: ", car);

  // useContext() retorna um objeto de contexto
  const { loginSession, setLoginSession } = useContext(LoginSessionContext);
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

  // Função assíncrona para buscar os carros por categoria e localização
  const getAllCarsByCategoryAndLocation = async (categoryId, locationId, token) => {
    try {
      const configuration = {
        headers: {
          Authorization: token,
        },
      };

      const response = await api.get(
        `/car/all_in_category_and_location?category_id=${categoryId}&location_id=${locationId}`, configuration
      );
      console.log(
        "response.data de getAllCarsByCategoryAndLocation(): ",
        response.data
      );
      setCars(response.data);
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao buscar os carros por categoria e localização.");
    }
  };

  // const handleChange = (event) => {
  //   const { name, value } = event.target;
  //   setFormData({
  //     ...formData,
  //     [name]: value,
  //   });
  // };

  const handleSubmit = (event) => {
    event.preventDefault();

    console.log("formData: ", formData);

    if (isEmpty(formData)) {
      alert("Preencha todos os campos!");
      return;
    }

    console.log("categoryId: ", formData.category.id);
    console.log("locationId: ", formData.location.id);

    getAllCarsByCategoryAndLocation(formData.category.id, formData.location.id, loginSession.token);

    // initialState = formData;
    // setFormData(initialState);
    // event.target.reset();
  };

  const fillTableRow = (car, index) => {
    return (
      <tr key={car.id}>
        <td>{car.id}</td>
        <td>{car.brand}</td>
        <td>{car.model}</td>
        <td>{car.color}</td>
        <td>{car.licensePlate}</td>
        <td>{car.modelYear}</td>
        <td>{car.mileage}</td>
        <td>
          {car.isAvailable ? (
            <Icon icon="fluent:presence-available-20-regular" color="green" />
          ) : (
            <Icon icon="gg:unavailable" color="red" />
          )}
        </td>
        <td>
          {/* <button className="iconEdit ">
            <Icon icon="material-symbols:edit-square-outline" />
          </button> */}
          <CarUpdateForm
            categories={categories}
            locations={locations}
            cars={cars}
            car={car}
            carIndex={index}
            category={formData.category}
            location={formData.location}
            token={loginSession.token}
            setCars={setCars}
          />
        </td>
      </tr>
    );
  };

  // Carregamento do loading
  if (categories.length === 0) {
    return <div className="loading">Loading&#8230;</div>;
  }

  return (
    <>
      {loginSession.isLogged && loginSession.user.fullName === "Admin" ? (
        <Helmet title="Car Update">
          {show ? (
            <Alert variant="success" onClose={() => setShow(false)} dismissible>
              Car updated successfully!
            </Alert>
          ) : null}
          <Container className="about_img d-flex  justify-content-center">
            <Form className="car-search-form" onSubmit={handleSubmit}>
              <h2 className="section_title">
                Search <Icon icon="maki:car" />
              </h2>
              {/* <Row className="mb-3">
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
                    autoFocus
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
                    autoFocus
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
                    autoFocus
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
                    autoFocus
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
                    autoFocus
                    required
                  />
                </Form.Group>
              </Row> */}

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
                        category: JSON.parse(event.target.value),
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
                        location: JSON.parse(event.target.value),
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

              {/* <Form.Group className="mb-3" id="formIsAvailableField">
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
              </Form.Group> */}

              <Button variant="secondary" type="submit">
                Search
              </Button>
            </Form>
          </Container>
          <Container>
            {cars.length > 0 ? (
              // <Form className="car-update-form">
              //   <table className="table table-responsive">
              //     <thead>
              //       <tr>
              //         <th>Brand</th>
              //         <th>Model</th>
              //         <th>Color</th>
              //         <th>License Plate</th>
              //         <th>Model Year</th>
              //         <th>Mileage</th>
              //       </tr>
              //     </thead>

              //     <tbody>
              //       {cars.map((car) => (
              //         <tr key={car.id}>
              //           <td>{car.brand}</td>
              //           <td>{car.model}</td>
              //           <td>{car.color}</td>
              //           <td>{car.licensePlate}</td>
              //           <td>{car.modelYear}</td>
              //           <td>{car.mileage}</td>
              //           <td>
              //             <button className="iconEdit ">
              //               <Icon icon="material-symbols:edit-square-outline" />
              //             </button>

              //             <button className="iconEdit ">
              //               {" "}
              //               <Icon icon="mingcute:save-line" />
              //             </button>
              //           </td>
              //         </tr>
              //       ))}
              //     </tbody>
              //   </table>
              // </Form>
              <Table striped bordered hover responsive>
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Color</th>
                    <th>License Plate</th>
                    <th>Model Year</th>
                    <th>Mileage</th>
                    <th>Availability</th>
                  </tr>
                </thead>
                <tbody>{cars.map((car, index) => fillTableRow(car, index))}</tbody>
              </Table>
            ) : (
              ""
            )}
          </Container>
        </Helmet>
      ) : null}
    </>
  );
};

export default CarUpdate;
