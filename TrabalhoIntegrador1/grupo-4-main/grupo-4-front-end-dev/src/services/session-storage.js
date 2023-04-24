// // Função para salvar o token no sessionStorage
// export const saveTokenInSessionStorage = (token) =>
//   sessionStorage.setItem("token", JSON.stringify(token));

// // Função para obter o token a partir do sessionStorage
// export const getTokenFromSessionStorage = () => {
//   const token = sessionStorage.getItem("token");
//   return token ? JSON.parse(token) : "";
// };

// Função para salvar o token no sessionStorage
export const saveTokenInSessionStorage = (token) =>
  sessionStorage.setItem("token", token);

// Função para obter o token a partir do sessionStorage
export const getTokenFromSessionStorage = () => {
  const token = sessionStorage.getItem("token");
  return token ? token : "";
};

// Função para remover o token do sessionStorage
export const removeTokenFromSessionStorage = () =>
  sessionStorage.removeItem("token");

// Função para verificar se tem token salvo no sessionStorage
// export const hasToken = () => Boolean(getTokenFromSessionStorage());
export const hasToken = () => !!getTokenFromSessionStorage();

// Função para armazenar as informações do usuário no sessionStorage
export const saveUserInSessionStorage = (user) =>
  sessionStorage.setItem("user", JSON.stringify(user));

// Função para obter o perfil do usuário a partir do sessionStorage
export const getUserFromSessionStorage = () => {
  const user = sessionStorage.getItem("user");
  return user ? JSON.parse(user) : {};
};

// Função para remover o usuário do sessionStorage
export const removeUserFromSessionStorage = () =>
  sessionStorage.removeItem("user");
