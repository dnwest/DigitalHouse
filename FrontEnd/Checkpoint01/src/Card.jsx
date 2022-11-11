function Card (props){

return(
    
  <div className="containerColor" style={{backgroundColor:`${props.card.color}`}}>
    <span className="nameColor">{props.card.name}</span>
    <p className="colorCode">{props.card.color}</p>
  </div>
  )
}

export default Card;