import React from "react";
import { Container, Row, Col } from "reactstrap";
import "../styles/about.css";
import aboutImg from "../assets/all-images/cars-img/new-bmw-offer.jpg";
import { Icon } from "@iconify/react";

const About = () => {
  return (
    <section>
      <Container>
        <Row className="justify-content-md-center align-items-center">
          <Col xs="12" md="6" lg="6">
            <div className="about_section-content">
              <h4 className="section_subtitle-h4">ABOUT US</h4>
              <h2 className="section_title">Want to know more about Alucar?</h2>
              <p>
                Alucar is a car rental company from Group 4, created as part of
                the Integrative Project. Our goal is to provide high-quality and
                reliable transportation solutions to our customers, always with
                a focus on their satisfaction and convenience.
              </p>

              <h2 className="section_title">Mission</h2>
              <p>
                Provide incredible experiences, offering everyone access to
                mobility with innovation and quality.
              </p>

              <h2 className="section_title">Vision</h2>
              <p>
                Be known throughout the country as a reference in mobility, with
                agile and sustainable growth.
              </p>

              <h2 className="section_title">Values</h2>
              <div className="d-flex align-items-center">
                <p className="section__description d-flex align-items-center gap-2">
                  <Icon icon="ri-checkbox-circle-line" /> Innovation
                </p>

                <p className="section__description d-flex align-items-center gap-2">
                  <Icon icon="ri-checkbox-circle-line" /> Honesty and Transparency
                </p>
              </div>

              <div className="d-flex align-items-center">
                <p className="section__description d-flex align-items-center gap-2">
                  <Icon icon="ri-checkbox-circle-line" /> Respect for People
                </p>

                <p className="section__description d-flex align-items-center gap-2">
                  <Icon icon="ri-checkbox-circle-line" />
                  Customer Focus
                </p>
              </div>

              <div className="d-flex align-items-center">
                <p className="section__description d-flex align-items-center gap-2">
                  <Icon icon="ri-checkbox-circle-line" /> Cultural Appreciation
                </p>

                <p className="section__description d-flex align-items-center gap-2">
                  <Icon icon="ri-checkbox-circle-line" />
                  Environmental Responsibility
                </p>
              </div>
              
            </div>
          </Col>

          <Col lg="6" md="6" >
            <div className="about_img d-flex  justify-content-end">
              <img
                src={aboutImg}
                alt=""
                className="w-100 h-100 object-fit-cover"
              />
            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default About;
