import Helmet from "../components/Helmet/Helmet";
import { Container, Row, Col } from "reactstrap";
import "swiper/css";
import ReservationCard from "../components/UI/ReservationCard";
import { useContext } from "react";
import { LoginSessionContext } from "../contexts/LoginSessionContext";
import Login from "../components/Account/Login";

const ReservationDetails = () => {
  const { loginSession } = useContext(LoginSessionContext);

  return (
    <Helmet title="Reservation Details">
      <section>
        <Container>
          <Row>
            <Col lg="12" className="text-center mb-5">
              <h2 className="section_title">Rates and additional</h2>
              <h6 className="section_subtitle">The best comfort for you</h6>
            </Col>
            {loginSession.isLogged ? <ReservationCard /> : <Login />}
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default ReservationDetails;
