import { Outlet } from "react-router-dom";
import Footer from "./Footer";
import Navbar from "./Navbar";
import AuthProvider from "../Components/AuthContext";


const DentistaRouter = () => {
  return (
    <div>
      <AuthProvider>
          <Navbar/>
          <Outlet />
          {/* {children} */}
          <Footer/>
      </AuthProvider>
    </div>
  );
};

export default DentistaRouter;
