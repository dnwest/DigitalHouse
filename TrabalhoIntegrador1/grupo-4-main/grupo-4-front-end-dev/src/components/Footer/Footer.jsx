import React from "react";
import { Container, Row, Col, ListGroup, ListGroupItem } from "reactstrap";
import { Link } from "react-router-dom";
import "../../styles/footer.css";
import { Icon } from "@iconify/react";

const quickLinks = [
  {
    path: "/about",
    display: "About",
  },

  {
    path: "/policy",
    display: "Privacy Policy",
  },

  {
    path: "/categories",
    display: "Categories",
  }
];

const Footer = () => {
  const date = new Date();
  const year = date.getFullYear();

  return (
    <footer className="footer footer_logo">
      <Container>
        <Row>
          <Col lg="4" md="4" sm="12">
            <div className="logo mb-3">
              <h1>
                <Link to="/home" className="d-flex align-items-center gap-2">
                  <Icon icon="ion:car-sport-outline" color="#d6d5c9" />
                  <span>
                    Alucar <br />
                    Rent Service
                  </span>
                </Link>
              </h1>
            </div>
            <p className="footer_logo-content mb-3">
              Rent a car with us and have a smooth and comfortable trip. Our
              well-maintained and diverse fleet ensures the best option for your
              needs. We offer competitive prices and excellent payment
              conditions. Book now and enjoy your trip with complete freedom.
            </p>
          </Col>

          <Col lg="2" md="4" sm="6">
            <div className="mb-3 media-only-none">
              <h5 className="footer_link-title">Quick Links</h5>
              <ListGroup>
                {quickLinks.map((item, index) => (
                  <ListGroupItem key={index} className="p-0 mt-3 quick__link">
                    <Link to={item.path}>{item.display}</Link>
                  </ListGroupItem>
                ))}
              </ListGroup>
            </div>
          </Col>

          <Col lg="3" md="4" sm="6">
            <div className="mb-3">
              <h5 className="footer_link-title">Head Office</h5>
              <div className="icons">
                <Icon icon="ic:baseline-location-on" color="#d6d5c9" />
                <p className="office_info">Paulista Avenue, Nº 543</p>
              </div>
              <div className="icons">
                <Icon icon="material-symbols:phone-forwarded" color="#d6d5c9" />
                <p className="office_info">Phone: +55 11 97438-2509</p>
              </div>
              <div className="icons">
                <Icon icon="ic:baseline-email" color="#d6d5c9" />
                <p className="office_info">Email: group-4@pi.com</p>
              </div>
              <div className="icons">
                <Icon
                  icon="material-symbols:nest-clock-farsight-analog-outline"
                  color="#d6d5c9"
                />
                <p className="office_info">Office Hours: 08:00 to 18:00</p>
              </div>
            </div>
          </Col>

          <Col lg="3" md="4" sm="6">
            <div className="mb-3 media-only-none">
              <h5 className="footer_link-title">Group 4</h5>
              <div className="icons">
                <a href="https://www.linkedin.com/in/juliane-oliveira-9b1b1b1b9/">
                  <Icon icon="carbon:logo-linkedin" />
                </a>
                <p className="office_info">Juliane</p>
              </div>

              <div className="icons">
                <a href="http://linkedin.com/in/rafael-oliveira-9b1b1b1b9">
                  <Icon icon="carbon:logo-linkedin" />
                </a>
                <p className="office_info">Geyson Kaio</p>
              </div>

              <div className="icons">
                <a href="https://www.linkedin.com/in/rafael-oliveira-9b1b1b1b9/">
                  <Icon icon="carbon:logo-linkedin" />
                </a>
                <p className="office_info">Thalles Denner</p>
              </div>
              <div className="icons">
                <a href="https://www.linkedin.com/in/rafael-oliveira-9b1b1b1b9/">
                  <Icon icon="carbon:logo-linkedin" />
                </a>
                <p className="office_info">Israel Almeida</p>
              </div>
              <div className="icons">
                <a href="https://www.linkedin.com/in/rafael-oliveira-9b1b1b1b9/">
                  <Icon icon="carbon:logo-linkedin" />
                </a>
                <p className="office_info">Christian Fernandes</p>
              </div>
              <div className="icons">
                <a href="https://www.linkedin.com/in/rafael-oliveira-9b1b1b1b9/">
                  <Icon icon="carbon:logo-linkedin" />
                </a>
                <p className="office_info">Vinicius Machado</p>
              </div>
              <div className="icons">
                <a href="https://www.linkedin.com/in/rafael-oliveira-9b1b1b1b9/">
                  <Icon icon="carbon:logo-linkedin" />
                </a>
                <p className="office_info">Antonio Henrique</p>
              </div>
            </div>
          </Col>
          <Col lg="12">
            <div className="footer_bottom">
              <p className="footer-copyright d-flex align-items-center justify-content-center gap-1 pt-3">
                <Icon icon="ri-copyright-line" />Copyright {year}, Developed
                by Group 4. All rights reserved.
              </p>
            </div>
          </Col>
          <span className="footer_social">
            <a href="https://www.facebook.com/">
              <Icon icon="bi:facebook" />
            </a>
            <a href="https://www.instagram.com/">
              <Icon icon="entypo-social:instagram-with-circle" />
            </a>
            <a href="https://www.twitter.com/">
              <Icon icon="ant-design:twitter-circle-filled" />
            </a>
            <a href="https://www.whatsapp.com/">
              <Icon icon="ri:whatsapp-fill" />
            </a>
          </span>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
