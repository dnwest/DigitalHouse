import { useState, useEffect } from "react";
import "../../styles/category-finder.css";
import { Button, Form, FormGroup, Input } from "reactstrap";
import { Link } from "react-router-dom";
import isEmpty from "../../utils/validations";
import api from "../../services/api";

const CategoryFinder = () => {
  const initialState = {
    pickupLocation: "",
    returnLocation: "",
    pickupDate: "",
    returnDate: "",
    pickupTime: "",
    returnTime: "",
  };

  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [formData, setFormData] = useState(initialState);
  const [locations, setLocations] = useState([]);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
  useEffect(() => {
    // Função assíncrona para buscar todas as localizações
    const getAllLocations = async () => {
      try {
        const response = await api.get("/location");
        // console.log("response.data de getAllLocations(): ", response.data);
        setLocations(response.data);
      } catch (error) {
        // console.log(error);
        alert("Ocorreu um erro ao buscar todas as localizações.");
      }
    };

    getAllLocations();
  }, []);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (isEmpty(formData)) {
      alert("Preencha todos os campos!");
      return;
    }

    if (formData.returnDate < formData.pickupDate) {
      alert("Data de retorno não pode ser menor do que a data de retirada!");
      return;
    }

    console.log("formData: " + formData);

    // initialState = formData;
    // setFormData(initialState);
  };

  return (
    <Form className="form" onSubmit={handleSubmit}>
      <div className="form-reserve">
        <div className="d-flex align-items-center justify-content-between flex-wrap">
          <FormGroup className="select_group form_group">
            <select
              id="From-address"
              name="pickupLocation"
              // onChange={handleChange}
              onChange={(event) =>
                setFormData({
                  ...formData,
                  pickupLocation: JSON.parse(event.target.value),
                })
              }
            >
              <option value="">Select pick up location</option>
              {locations.map((location) => (
                <option key={location.id} value={JSON.stringify(location)}>
                  {location.city}
                </option>
              ))}
            </select>
          </FormGroup>

          <FormGroup className="form_group">
            <Input
              className="journey_date"
              type="date"
              placeholder="Date"
              onChange={handleChange}
              name="pickupDate"
              value={formData.pickupDate}
              required
            />
          </FormGroup>

          <FormGroup className="form_group">
            <Input
              className="journey_time"
              type="time"
              placeholder="Time"
              onChange={handleChange}
              name="pickupTime"
              value={formData.pickupTime}
              required
              pattern="[0-9]{2}:00"
              onFocus={(e) => {
                e.currentTarget.setAttribute("step", "3600");
                e.currentTarget.setAttribute("pattern", "[0-9]{2}:00");
              }}
            />
          </FormGroup>


          <FormGroup className="select_group form_group">
            <select
              id="To-address"
              name="returnLocation"
              // onChange={handleChange}
              onChange={(event) =>
                setFormData({
                  ...formData,
                  returnLocation: JSON.parse(event.target.value),
                })
              }
            >
              <option value="">Select return location</option>
              {locations.map((location) => (
                <option key={location.id} value={JSON.stringify(location)}>
                  {location.city}
                </option>
              ))}
            </select>
          </FormGroup>

          <FormGroup className="form_group">
            <Input
              className="journey_date"
              type="date"
              placeholder="Date"
              onChange={handleChange}
              name="returnDate"
              value={formData.returnDate}
              required
            />
          </FormGroup>

          <FormGroup className="form_group">
            <Input
              className="journey_time"
              type="time"
              placeholder="Time"
              onChange={handleChange}
              name="returnTime"
              value={formData.returnTime}
              required
              pattern="[0-9]{2}:00"
              onFocus={(e) => {
                e.currentTarget.setAttribute("step", "3600");
                e.currentTarget.setAttribute("pattern", "[0-9]{2}:00");
              }}
            />
          </FormGroup>

          <FormGroup className="form_group">
            <Button className="find__car-btn btn" type="submit">
              <Link
                to={"/reservation/step-1"}
                state={{ searchParameters: formData }}
                className="find__car-btn"
              >
                Search
              </Link>
            </Button>
          </FormGroup>
        </div>
      </div>
    </Form>
  );
};

export default CategoryFinder;
