import { useContext, useRef } from "react";
import { Container, Row, Col } from "reactstrap";
import { NavLink } from "react-router-dom";
import "../../styles/header.css";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import { Icon } from "@iconify/react";
import Avatar from "../UI/Avatar";
import AdminLogout from "./AdminLogout";

const navLinks = [
  {
    path: "/admin/car_registration",
    display: "Car Registration",
  },

  {
    path: "/admin/car_update",
    display: "Car Update",
  },
];

const AdminHeader = () => {
  const menuRef = useRef(null);

  const toggleMenu = () => menuRef.current.classList.toggle("menu_active");

  const { loginSession } = useContext(LoginSessionContext);

  return (
    <header className="header">
      {/* ==== header top ==== */}
      <div className="header_top">
        <Container>
          <Row>
            <Col lg="6" md="6" sm="6">
              <div className="header_top_left">
                <span className="navbar-toggler-icon"></span>
                <Icon icon="clarity:administrator-solid" />
                <span>ADMIN</span>
                {/* <span className="header_top_help">
                  <Icon icon="ri:phone-fill" className="header_top_help_icon" />{" "}
                  +55 11 97438-2509
                </span> */}
              </div>
            </Col>
            <Col lg="6" md="6" sm="6">
              <div className="header_top_right">
                {loginSession.isLogged ? (
                  <Avatar fullName={loginSession.user.fullName} />
                ) : null}

                <AdminLogout />
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

      {/* ==== main navigation ==== */}
      {loginSession.isLogged && loginSession.user.fullName === "Admin" ? (
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
                  {navLinks.map((item, index) => (
                    <NavLink
                      to={item.path}
                      className={(navClass) =>
                        navClass.isActive ? "nav_active nav_item" : "nav_item"
                      }
                      key={index}
                    >
                      {item.display}
                    </NavLink>
                  ))}
                </div>
              </div>
              <div className="nav_right"></div>
            </div>
          </Container>
        </div>
      ) : null}
    </header>
  );
};

export default AdminHeader;
