import { Outlet } from "react-router-dom";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect } from "react";

const Account = () => {
  // useLocation retorna o objeto de localização atual (rota para o componente App)
  const location = useLocation();

  // useNavigate retorna uma função que permite navegar programaticamente
  const navigate = useNavigate();

  // useEffect permite executar efeitos colaterais (será executada toda vez que o componente for renderizado)
  useEffect(() => {
    if (location.pathname === "/account" || location.pathname === "/account/") {
      navigate("/account/login");
    }
  });
  return <Outlet />;
};

export default Account;
