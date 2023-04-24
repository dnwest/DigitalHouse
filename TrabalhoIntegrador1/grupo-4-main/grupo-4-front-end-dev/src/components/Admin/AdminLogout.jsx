import { Link } from "react-router-dom";
import { Icon } from "@iconify/react";
import { useContext } from "react";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";

const AdminLogout = () => {
  const { loginSession, setLoginSession } = useContext(LoginSessionContext);

  const logOut = () => {
    // setLoginSession({ ...loginSession, isLogged: false });
    setLoginSession({ isLogged: false, token: "", user: "" });
  };

  return (
    <>
      {loginSession.isLogged && loginSession.user.fullName === "Admin" ? (
        <Link
          className="d-flex align-items-center gap-1"
          onClick={logOut}
          to={"/admin/login"}
        >
          <Icon icon="ri-user-line" className="header_top_right_icon" /> Log out
        </Link>
      ) : null}
    </>
  );
};

export default AdminLogout;
