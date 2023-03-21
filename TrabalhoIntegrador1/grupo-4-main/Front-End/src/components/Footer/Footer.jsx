import React from "react";
import { Container, Row, Col, ListGroup, ListGroupItem } from "reactstrap";
import { Link } from "react-router-dom";
import "../../styles/footer.css";

const quickLinks = [
  {
    path: "/about",
    display: "About",
  },

  {
    path: "#",
    display: "Privacy Police",
  },

  {
    path: "/cars",
    display: "Cars",
  },

  {
    path: "/blogs",
    display: "Blog",
  },

  {
    path: "/contact",
    display: "Contact",
  },
];

const Footer = () => {
  const date = new Date();
  const year = date.getFullYear();

  return (
    <footer className="footer footer_logo">
      <Container>
        <Row>
          <Col lg="4" md="4" sm="12">
            <div className="logo ">
              <h1>
                <Link to="/home" className="d-flex align-items-center gap-2">
                  <i class="ri-car-line"></i>
                  <span>
                    Alucar <br />
                    Rent Service
                  </span>
                </Link>
              </h1>
            </div>
            <p className="footer_logo-content">
              Rent a car with us and have a smooth and comfortable trip. Our
              well-maintained and diverse fleet ensures the best option for your
              needs. We offer competitive prices and excellent payment
              conditions. Book now and enjoy your trip with complete freedom.
            </p>
          </Col>

          <Col lg="2" md="4" sm="6">
            <div className="mb-3">
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
              <h5 className="footer_link-title mb-3">Head Office</h5>
              <p className="office_info">
                Paulista Avenue, 1000, São Paulo City, Brazil
              </p>
              <p className="office_info">Phone: +55 11 97438-2509</p>
              <p className="office_info">Email: integration-project@pi.com</p>
              <p className="office_info">Office Hours: 10am to 7pm</p>
            </div>
          </Col>

          <Col lg="3" md="4">
            <div className="mb-3">
              <h5 className="footer_link-title">Newsletter</h5>
              <p className="section_description">Subscribe our newsletter</p>
              <div className="newsletter">
                <input
                  type="email"
                  name="email"
                  id="email"
                  placeholder="Email"
                />
                <span>
                  <i class="ri-send-plane-line"></i>
                </span>
              </div>
            </div>
          </Col>

          <Col lg="12">
            <div className="footer_bottom">
              <p className="section_description d-flex align-items-center justify-content-center gap-1 pt-3">
                <i class="ri-copyright-line"></i>Copyright {year}, Developed by
                Group 4. All rights reserved.
              </p>
            </div>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
