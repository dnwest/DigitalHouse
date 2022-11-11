import FormCard from './FormCard';
import Card  from './Card';
import './index.css'
import './Card.css'
import { useState } from 'react';


const App = () => {
    const [name, setName,] = useState("")
    const [color, setColor] = useState("")
    const [card, setCard] = useState([])
    const [error, setError] = useState([])
return(
<>
    <div><FormCard
    name={name}
    setName={setName}
    color={color}
    setColor={setColor}
    card={card}
    setCard={setCard}
    error={error}
    setError={setError}
    /></div>
    <span className='error'>{error}</span>
    <h1>Cores Favoritas</h1>
    <div className="container">{card.map((card)=>{return(<Card card={card}/>)})}</div>
</>

)
}

export default App;