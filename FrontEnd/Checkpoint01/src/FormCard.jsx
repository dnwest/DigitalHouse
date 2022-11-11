function FormCard (props){

  function onSubmit(event) {
    event.preventDefault()
    props.setError('')

   if (props.name.length < 3) {
    props.setError('Verifique os dados inseridos - mínimo de 3 caracteres no campo nome')
    return
  }

  if (props.name.startsWith(' ')) {
    props.setError('Verifique os dados inseridos - não é permitido espaço')
    return
  }

  if (props.color.length < 6) {
    props.setError('Verifique os dados inseridos - mínimo de 6 caracteres no campo cor')
    return
  }

  if (!(/\d/.test(props.color))) {
    props.setError('Verifique os dados inseridos, necessário um caractere numérico no campo cor')
    return
  }

   else {
    props.setCard([...props.card, {
      name: props.name,
      color: props.color,
    }])
  } 
 

  }

return(
  <div className="container">
<div className="containerForm">
<form onSubmit={onSubmit}>
  <h1>Adicionar nova cor</h1>
  <label className="flexContainer">
    Nome:
    <input className="" value={props.name} onChange={(event)=> props.setName(event.target.value)} />
  </label>
  <label>
    Cor:
    <input className="" value={props.color} placeholder='Insira sua cor no formato Hexadecimal' onChange={(event)=> props.setColor(event.target.value)} />
  </label>
  <div className="flexButton">
  <button type="submit">Adicionar</button>
  </div>
  </form>
  </div>
  </div>
  
  )
}



export default FormCard;
