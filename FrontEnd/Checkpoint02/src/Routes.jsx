import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import AuthProvider, { AuthContext } from "./Components/AuthContext";
import { useContext } from "react";
import DentistaRouter from "./Components/DentistaRouter";
import DetailCard from "./pages/DetailCard";

const PrivateRoute = ({ children }) => {
  const { userData } = useContext(AuthContext);
  return userData.token ? children : <Navigate to={"/"} />;
};

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/auth" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/home" element={<DentistaRouter />}>
            <Route path="" element={<PrivateRoute><Home /></PrivateRoute>} />
          </Route>
          <Route path="/dentist" element={<DentistaRouter />}>
            <Route path=":matricula" element={<PrivateRoute><DetailCard /></PrivateRoute>} />
          </Route>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
};

export default AppRoutes;
