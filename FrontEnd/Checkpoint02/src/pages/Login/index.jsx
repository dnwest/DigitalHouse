import LoginForm from "../../Components/LoginForm";
import Navbar from "../../Components/Navbar";
import { AuthContext } from "../../Components/AuthContext";
import { useContext, useEffect } from "react";
import style from "./Login.module.css";

const Login = () => {

  const {setIsLogado} = useContext(AuthContext);

  useEffect(()=>{
    setIsLogado(false);
  },[]);

  return (
    <div className={style.loginContainer}>
        <Navbar />
          <h1>Login</h1>
          <LoginForm />
     </div>
  );
};

export default Login;
