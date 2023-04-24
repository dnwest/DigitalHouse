import { useContext, useRef } from "react";
import { useLocation } from "react-router-dom";
import { Container, Row, Col } from "reactstrap";
import { Link, NavLink } from "react-router-dom";
import "../../styles/header.css";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import Login from "../Account/Login";
import { Icon } from "@iconify/react";
import Avatar from "../UI/Avatar";

const navLinks = [
  {
    path: "/home",
    display: "Home",
  },

  {
    path: "/categories",
    display: "Categories",
  },
  {
    path: "/reservation/history",
    display: "Reservations",
  },
  {
    path: "/about",
    display: "About",
  },
  {
    path: "/policy",
    display: "Policy",
  },
  {
    path: "/account/login",
    display: "Log in",
  },
];

const Header = () => {
  const menuRef = useRef(null);

  const toggleMenu = () => menuRef.current.classList.toggle("menu_active");

  // useLocation retorna o objeto de localização atual
  const location = useLocation();
  console.log("location: ", location.pathname);

  const { loginSession, setLoginSession } = useContext(LoginSessionContext);

  return (
    <header className="header">
      {/* ==== header top ==== */}
      <div className="header_top">
        <Container>
          <Row>
            <Col lg="6" md="6" sm="6">
              <div className="header_top_left">
                <span className="navbar-toggler-icon"></span>
                <span>Need Help?</span>
                <span className="header_top_help">
                  <Icon icon="ri:phone-fill" className="header_top_help_icon" />{" "}
                  +55 11 97438-2509
                </span>
              </div>
            </Col>
            <Col lg="6" md="6" sm="6">
              <div className="header_top_right">
                {loginSession.isLogged ? (
                  <Avatar fullName={loginSession.user.fullName} />
                ) : null}

                {!(
                  location.pathname === "/account/login" ||
                  location.pathname === "/account/signup" ||
                  (location.pathname === "/account/recovery" &&
                    loginSession.isLogged) ||
                  (location.pathname === "/reservation/step-2" &&
                    !loginSession.isLogged)
                ) && <Login />}
              </div>
            </Col>
            {/* <Col lg="6" md="6" sm="6">
              <div className="header_top_right">
                {loginSession.isLogged ? (
                  <Col>
                    <Link to="/reservation/history">My reservations</Link>
                  </Col>
                ) : null}
              </div>
            </Col> */}
          </Row>
        </Container>
      </div>

      {/* ==== header middle ==== */}
      <div className="header_middle">
        <Container>
          <Row>
            <Col lg="4" md="3" sm="4">
              <div className="logo">
                <h1>
                  <Link to="/home" className="d-flex align-items-center gap-2">
                    <Icon icon="ion:car-sport-outline" color="#554d43" />
                    <span>
                      Alucar <br />
                      Rent Service
                    </span>
                  </Link>
                </h1>
              </div>
            </Col>

            <Col lg="3" md="3" sm="4">
              <div className="header_location d-flex align-items-center gap-2">
                <span>
                  <Icon icon="ri-earth-line" className="header_location_icon" />
                </span>
                <div className="header_location-content">
                  <address>
                    <h4>Brazil</h4>
                    <h6>São Paulo, Brazil</h6>
                  </address>
                </div>
              </div>
            </Col>

            <Col lg="3" md="3" sm="4">
              <div className="header_location d-flex align-items-center gap-2">
                <span>
                  <Icon icon="ri-time-line" className="header_location_icon" />
                </span>
                <div className="header_location-content">
                  <h4>Everyday</h4>
                  <h6>
                    <time>08:00 - 18:00</time>
                  </h6>
                </div>
              </div>
            </Col>

            <Col
              lg="2"
              md="3"
              sm="0"
              className="d-flex align-items-center justify-content-end"
            >
              <button className="header_btn btn  text-end">
                <Link to="/contact">
                  <Icon icon="ri-phone-line" className="header_call_icon" />
                  Request a call
                </Link>
              </button>
            </Col>
          </Row>
        </Container>
      </div>

      {/* ==== main navigation ==== */}

      <div className="main_navbar">
        <Container>
          <div className="navigation_group d-flex align-items-center justify-content-between">
            {/* Togle menu suspenso */}

            <span className="navbar-toggler-icon"></span>
            <span className="mobile_menu">
              <Icon icon="ic:outline-menu" onClick={toggleMenu} />
            </span>

            <div className="navigation" ref={menuRef} onClick={toggleMenu}>
              <div className="menu">
                {navLinks.map((item, index) => {
                  if (
                    (item.path === "/reservation/history" &&
                      !loginSession.isLogged) ||
                    (item.path === "/account/login" && loginSession.isLogged)
                  ) {
                    return null;
                  } else {
                    return (
                      <NavLink
                        to={item.path}
                        className={(navClass) =>
                          navClass.isActive ? "nav_active nav_item" : "nav_item"
                        }
                        key={index}
                      >
                        {item.display}
                      </NavLink>
                    );
                  }
                })}
                {loginSession.isLogged ? (
                  <Link className="nav_item"
                    onClick={() => {
                      setLoginSession({ isLogged: false, token: "", user: "" });
                    }}
                    to={"/home"}
                  >
                    Log out
                  </Link>
                ) : null}
              </div>
            </div>
            <div className="nav_right"></div>
          </div>
        </Container>
      </div>
    </header>
  );
};

export default Header;
