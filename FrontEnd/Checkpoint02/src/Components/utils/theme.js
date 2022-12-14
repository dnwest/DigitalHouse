import { createContext, useMemo, useReducer } from "react";
//import { actions, initialState, reducer } from "./reducer.service";

const initialState = { theme: "light"};

export const actions = {
    SET_THEME_LIGHT: "SET_THEME_LIGHT",
    SET_THEME_DARK: "SET_THEME_DARK",
}

export const reducer = (state, action) =>{
    switch (action.type) {
        case actions.SET_THEME_DARK:
            return ({...state, theme: "dark"})
        case actions.SET_THEME_LIGHT:
            return  ({...state, theme: "light"})
        default: {
            return state}
    }
}


export const ContextGlobal = createContext(undefined);

export const ContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const providerState = useMemo(
    () => ({
      theme: state.theme,
      setDarkTheme: () => {
        dispatch({ type: actions.SET_THEME_DARK });
      },
      setLightTheme: () => {
        dispatch({ type: actions.SET_THEME_LIGHT });
      },
    }),
    [state.theme]
  );

  return (
    <ContextGlobal.Provider value={providerState}>
      {children}
    </ContextGlobal.Provider>
  );
};

