import React from "react";
import { Navigate, Routes, Route } from "react-router-dom";
import Home from "../pages/Home";
import Reservation from "../pages/Reservation";
import About from "../pages/About";
import Blog from "../pages/Blog";
import NotFound from "../pages/NotFound";
import Categories from "../pages/Categories";
import AvailableCategories from "../pages/AvailableCategories";
import ReservationDetails from "../pages/ReservationDetails";
import Signup from "../components/Account/Signup";
import Account from "../pages/Account";
import Login from "../components/Account/Login";
import AccountRecovery from "../components/Account/AccountRecovery";
import ReservationHistory from "../components/UI/ReservationHistory";
import Contact from "../pages/Contact";
import Policy from "../pages/Policy";
import Admin from "../pages/Admin/Admin";
import CarRegistration from "../pages/Admin/CarRegistration";
import CarUpdate from "../pages/Admin/CarUpdate";
import AdminLogin from "../components/Admin/AdminLogin";

const Routers = () => {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" />} />
      <Route path="admin" element={<Admin />}>
        <Route path="login" element={<AdminLogin />} />
        <Route path="car_registration" element={<CarRegistration />} />
        <Route path="car_update" element={<CarUpdate />} />
      </Route>
      <Route path="account" element={<Account />}>
        <Route path="signup" element={<Signup />} />
        <Route path="login" element={<Login />} />
        <Route path="recovery" element={<AccountRecovery />} />
      </Route>
      <Route path="home" element={<Home />} />
      <Route path="categories" element={<Categories />} />
      <Route path="reservation" element={<Reservation />}>
        <Route path="step-1" element={<AvailableCategories />} />
        <Route path="step-2" element={<ReservationDetails />} />
        <Route path="history" element={<ReservationHistory />} />
      </Route>
      <Route path="about" element={<About />} />
      <Route path="blog" element={<Blog />} />
      <Route path="contact" element={<Contact />} />
      <Route path="policy" element={<Policy />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

export default Routers;
