import { FormColor } from './FormColor';
import { Card } from './Card';
import './index.css'
import './Card.css'


const App = () => <>
    <div>{<FormColor />}</div>
    <h1>Cores Favoritas</h1>
    <div className="container">{<Card />}{<Card />}{<Card />}{<Card />}{<Card />}</div>
</>

export default App;