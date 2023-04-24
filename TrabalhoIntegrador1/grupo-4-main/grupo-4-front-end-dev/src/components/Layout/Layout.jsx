import React, { Fragment } from "react";

import Header from "../Header/Header";
import Footer from "../Footer/Footer";
import Routers from "../../routers/Routers";
import AdminHeader from "../Admin/AdminHeader";
import { useLocation } from "react-router-dom";
import AdminFooter from "../Admin/AdminFooter";

const Layout = () => {
  // useLocation retorna o objeto de localização atual
  const location = useLocation();
  // console.log("location: ", location.pathname);

  return (
    <Fragment>
      {location.pathname === "/admin/login" ||
      location.pathname === "/admin/car_registration" ||
      location.pathname === "/admin/car_update" ? (
        <AdminHeader />
      ) : (
        <Header />
      )}
      <div>
        <Routers />
      </div>
      {location.pathname === "/admin/login" ||
      location.pathname === "/admin/car_registration" ||
      location.pathname === "/admin/car_update" ? (
        <AdminFooter />
      ) : (
        <Footer />
      )}
    </Fragment>
  );
};

export default Layout;
