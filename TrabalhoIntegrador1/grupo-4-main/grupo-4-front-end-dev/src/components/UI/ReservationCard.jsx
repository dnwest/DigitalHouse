import "../../styles/categories.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Navigate, useLocation, useNavigate } from "react-router-dom";
import { useContext, useState, useEffect } from "react";
import api from "../../services/api";
import Login from "../Account/Login";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import { Container, Row, Col } from "reactstrap";
import "../../styles/about.css";
import "../../styles/policy.css";
import Carousel from "react-bootstrap/Carousel";

const ReservationCard = () => {
  const { loginSession } = useContext(LoginSessionContext);

  // useLocation retorna o objeto de localização atual
  const location = useLocation();
  const searchParameters = location.state?.searchParameters;
  const category = location.state?.category;
  // console.log("searchParameters: ", searchParameters);

  // const { id, name, description, price, images, features } = category;
  // console.log("Category: ", category);

  const [order, setOrder] = useState({
    categoryId: category?.id,
    pickupDatetime: new Date(
      `${searchParameters?.pickupDate} ${searchParameters?.pickupTime}`
    ),
    returnDatetime: new Date(
      `${searchParameters?.returnDate} ${searchParameters?.returnTime}`
    ),
  });

  const [totalInvoiceAmount, setTotalInvoiceAmount] = useState(null);

  const [reservation, setReservation] = useState({});

  const navigate = useNavigate();

  // Estado inicial do modal de login = false
  // const [showLogin, setShowLogin] = useState(false);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
  useEffect(() => {
    // Função assíncrona para buscar todas as categorias da localização do usuário
    const calculateTotalInvoiceAmount = async (token) => {
      try {
        // const request = {
        //   categoryId: id,
        //   pickupDatetime: new Date(`${searchParameters.pickupDate} ${searchParameters.pickupTime}`),
        //   returnDatetime: new Date(`${searchParameters.returnDate} ${searchParameters.returnTime}`),
        // };

        const configuration = {
          method: "post",
          maxBodyLength: Infinity,
          url: "/reservation/total_invoice_amount",
          headers: {
            Authorization: token,
            "Content-Type": "application/json",
          },
          data: order,
        };

        // const response = await api.post(
        //   "/reservation/total_invoice_amount",
        //   order
        // );

        const response = await api.request(configuration);
        console.log(
          "response.data de calculateTotalInvoiceAmount(): ",
          response.data
        );
        setTotalInvoiceAmount(response.data);
      } catch (error) {
        // console.log("error: ", error);
        alert("Ocorreu um erro ao calcular o valor total da fatura.");
      }
    };

    if (order.categoryId !== undefined) {
      calculateTotalInvoiceAmount(loginSession.token);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [order]);

  // Função assíncrona para realizar reserva
  const bookNow = async (token) => {
    try {
      const data = JSON.stringify({
        clientId: loginSession.user.id,
        categoryId: category.id,
        pickupLocationId: searchParameters.pickupLocation.id,
        returnLocationId: searchParameters.returnLocation.id,
        pickupDatetime: new Date(
          `${searchParameters.pickupDate} ${searchParameters.pickupTime}`
        ),
        returnDatetime: new Date(
          `${searchParameters.returnDate} ${searchParameters.returnTime}`
        ),
      });

      const configuration = {
        method: "post",
        maxBodyLength: Infinity,
        url: "/reservation",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
        data: data,
      };

      const response = await api.request(configuration);
      console.log("response.data de bookNow(): ", response.data);
      setReservation(response.data);
    } catch (error) {
      // console.log("error: ", error);
      alert("Ocorreu um erro ao realizar a reserva.");
    }
  };

  const handleClick = () => {
    if (loginSession.isLogged) {
      bookNow(loginSession.token);
      navigate("/reservation/history", { state: reservation });
    }
  };

  return (
    <>
      {searchParameters === undefined ? (
        <Navigate to={"/home"} />
      ) : (
        <div className="reserva-content">
          <div className="category-img">
            {/* <img
              className="img-card-reserva"
              src={`${category.images[0].url}`}
              alt="alt"
            /> */}
            <Carousel fade>
              {category.images.map((image) => (
                <Carousel.Item key={image.id}>
                  <img
                    className="d-block w-100 img-card-reserva"
                    src={image.url}
                    alt={image.description}
                  />
                </Carousel.Item>
              ))}
            </Carousel>

            <span className="car-name">{category.name}</span>
            <span className="card-price">U$ {category.price}</span>

            <div className="buttom-confirm-reserv">
              {/* {loginSession.isLogged ? (
                <button
                  className="car_item-btn-reserv car_btn-details-reserv"
                  onClick={handleClick}
                >
                  Continue
                </button>
              ) : (
                <Login />
              )} */}
              <button
                className="car_item-btn-reserv car_btn-details-reserv"
                onClick={handleClick}
              >
                Confirm
              </button>
            </div>
          </div>

          <div className="category-resumo">
            <div>
              <div className="content-resumo">
                <h1>Reservation Summary</h1>
              </div>
            </div>

            <div className="retirada">
              <h1>Pick Up</h1>
              <h2>Date: {searchParameters.pickupDate}</h2>
              <h2>Time: {searchParameters.pickupTime}</h2>
              <h3>Location: {searchParameters.pickupLocation.city}</h3>
            </div>

            <div className="devolucao">
              <h1>Return</h1>
              <h2>Date: {searchParameters.returnDate}</h2>
              <h2>Time: {searchParameters.returnTime}</h2>
              <h3>Location: {searchParameters.returnLocation.city}</h3>
            </div>

            {/* TODO: implementar uma classe css para essa parte */}
            <div className="devolucao">
              <h1>Invoice</h1>
              <h2>Total: U$ {totalInvoiceAmount}</h2>
            </div>
          </div>
        </div>
      )}
      <section>
        <Container>
          <Row className="align-items-center">
            <Col lg="6" md="6">
              <div className="about_section-content policy">
                <div className="about_section-content">
                  <h4 className="section_subtitle">BOOKING POLICY</h4>
                  <h2 className="section_title">What we cherish:</h2>
                  <p className="section_description">
                    Reservations can be made via the website and by phone and
                    are subject to availability of the reserved category. Alucar
                    does not guarantee the vehicle model, only the category and,
                    if the reserved category is not available at the time of
                    pick-up, Alucar will provide a higher category vehicle for
                    the same amount charged. The reservation is guaranteed for a
                    period of up to 1 (one) hour after the scheduled time for
                    the withdrawal, as long as this tolerance time is in the
                    period of normal operation of the store.
                  </p>

                  <h3 className="section_title">Cancellation </h3>
                  <p className="section_description">
                    In case of cancellation, it can be done online free of
                    charge up to 48 (Forty-eight) hours before the start of the
                    rental. Car rental reservations canceled within 48
                    (Forty-eight) hours of starting pick-up will be charged a
                    fee equivalent to a three-day rental. The cancellation fee
                    will never be greater than the cost of the car rental.
                  </p>
                </div>
              </div>
            </Col>
          </Row>
        </Container>
      </section>
    </>
  );
};

export default ReservationCard;
