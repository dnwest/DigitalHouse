import AppRoutes from "./Routes";
import { NavBarContext } from "./Components/NavBarContext";
import { useContext, useEffect } from "react";

function App() {
  useEffect(() => {}, []);

  const { contextIsLight } = useContext(NavBarContext);

  return (
    <>
      <div className={contextIsLight ? `app light` : `app dark`}>
        <AppRoutes />
      </div>
    </>
  );
}

export default App;
