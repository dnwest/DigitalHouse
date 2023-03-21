import React from "react";
import { Container, Row, Col } from "reactstrap";
import "../../styles/about-section.css";
import aboutImg from "../../assets/all-images/cars-img/new-bmw-offer.jpg";

const AboutSection = () => {
  return (
    <section>
      <Container>
        <Row className="align-items-center">
          <Col lg="6" md="6">
            <div className="about_section-content">
              <h4 className="section_subtitle">About Us</h4>
              <h2 className="section_title">Want to know more about Alucar?</h2>
              <p className="section_description">
                Alucar is a car rental company from Group 4, created as part of
                the Integrative Project. Our goal is to provide high-quality and
                reliable transportation solutions to our customers, always with
                a focus on their satisfaction and convenience.
              </p>

              <div className="about_section-item d-flex align-items-center">
                <p className="section_description d-flex align-items-center gap-2">
                  <i className="ri-checkbox-circle-line"></i> The Group 4,
                  Rocks!
                </p>

                <p className="section_description d-flex align-items-center gap-2">
                  <i className="ri-checkbox-circle-line"></i> The Group 4,
                  Rocks!
                </p>
              </div>

              <div className="about_section-item d-flex align-items-center">
                <p className="section_description d-flex align-items-center gap-2">
                  <i className="ri-checkbox-circle-line"></i> The Group 4,
                  Rocks!
                </p>

                <p className="section_description d-flex align-items-center gap-2">
                  <i className="ri-checkbox-circle-line"></i>The Group 4, Rocks!
                </p>
              </div>
            </div>
          </Col>

          <Col lg="6" md="6">
            <div className="about_img d-flex  justify-content-end">
              <img
                src={aboutImg}
                alt=""
                className="w-100 h-100 object-fit-cover object-position-right"
              />
            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default AboutSection;
