function FormColor(props){

return(
  <div className="container">
<div className="containerForm">
<form>
  <h1>Adicionar nova cor</h1>
  <label className="flexContainer">
    Nome:
    <input type="text" name="name" />
  </label>
  <label>
    Cor:
    <input type="text" name="name" />
  </label>
  <div className="flexButton">
  <button type="submit">Adicionar</button>
  </div>
  </form>
  </div>
  </div>
  )
}

export default FormColor;
