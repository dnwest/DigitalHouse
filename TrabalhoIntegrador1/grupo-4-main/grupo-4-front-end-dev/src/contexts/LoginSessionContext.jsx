import { createContext, useEffect, useState } from "react";
import {
  hasToken,
  getTokenFromSessionStorage,
  getUserFromSessionStorage,
  saveTokenInSessionStorage,
  saveUserInSessionStorage,
  removeTokenFromSessionStorage,
  removeUserFromSessionStorage,
} from "../services/session-storage";

// Estado inicial
const initialState = {
  isLogged: hasToken(),
  token: getTokenFromSessionStorage(),
  user: getUserFromSessionStorage(),
};

// Criação de um objeto de contexto
export const LoginSessionContext = createContext();

// Provedor de contexto
const LoginSessionContextProvider = ({ children }) => {
  // Variável de estado e função de mudança de estado
  const [loginSession, setLoginSession] = useState(initialState);
  console.log("loginSessionContext: ", loginSession);

  // Função de efeito colateral
  useEffect(() => {
    // if (Object.keys(loginSession).length === 0) {
    //   removeTokenFromSessionStorage();
    //   removeUserFromSessionStorage();
    // } else {
    //   saveTokenInSessionStorage(loginSession.token);
    //   saveUserInSessionStorage(loginSession.user);
    // }
    if (loginSession.isLogged) {
      saveTokenInSessionStorage(loginSession.token);
      saveUserInSessionStorage(loginSession.user);
    } else {
      removeTokenFromSessionStorage();
      removeUserFromSessionStorage();
    }
  }, [loginSession]);

  return (
    // A propriedade value contém o que pode ser acessado de LoginSessionContext
    <LoginSessionContext.Provider value={{ loginSession, setLoginSession }}>
      {children}
    </LoginSessionContext.Provider>
  );
};

export default LoginSessionContextProvider;
