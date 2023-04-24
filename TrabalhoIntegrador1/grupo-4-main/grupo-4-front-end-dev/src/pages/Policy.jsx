import React from "react";
import { Container, Row, Col } from "reactstrap";
import "../styles/about.css";
import "../styles/policy.css"
import { Icon } from "@iconify/react";
import { Link } from "react-router-dom";

const About = () => {
  return (
    <section>
      <Container>
        <Row className="align-items-center">
          <Col lg="6" md="6">
            <div className="about_section-content policy">
              <div className="about_section-content">
              <h4 className="section_subtitle-h4">BOOKING POLICY</h4>
              <h2 className="section_title">What we cherish:</h2>
              <p className="section_description">
              Reservations can be made via the website and by phone and are
              subject to availability of the reserved category. Alucar does not guarantee
              the vehicle model, only the category and, if the reserved category
              is not available at the time of pick-up, Alucar will provide
              a higher category vehicle for the same amount charged. The reservation is
              guaranteed for a period of up to 1 (one) hour after the scheduled time for the
              withdrawal, as long as this tolerance time is in the period of
              normal operation of the store.
              </p>

              <h3 className="section_title">Cancellation </h3>
              <p className="section_description" >In case of cancellation, it can be done online free of charge up to 48 (Forty-eight) hours before the start of the rental. Car rental reservations canceled within 48 (Forty-eight) hours of starting pick-up will be charged a fee equivalent to a three-day rental. The cancellation fee will never be greater than the cost of the car rental.</p>
              </div>
              <div className="about_section_content">
              <Link className="d-flex align-items-center gap-2">
                  <Icon icon="ion:car-sport-outline" color="#d6d5c9" height={"300px"}/>
                </Link>
              </div>

            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default About;
