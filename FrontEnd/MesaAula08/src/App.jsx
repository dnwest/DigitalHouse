import { useState } from 'react';
import './App.css';

const App = () => {
  
  const [data, setData] = useState([]);

  const [nome, setNome] = useState("");
  const [idade, setIdade] = useState("");
  const [pokemon, setPokemon] = useState("");

  const [errorInput, setErrorInput] = useState("");



  function handleSubmit(event) {
    event.preventDefault();
    if (nome.trim()) {
      (setData([...data, nome, idade, pokemon]));

      setErrorInput("");
      setNome("");
      setIdade("");
      setPokemon("");
  } else {
    setErrorInput("Preencha os campos corretamente.")
  }
}

  return (
    <>

      <form onSubmit={handleSubmit} className="form">
        <input value={nome} placeholder="Nome" onChange={(event) => setNome(event.target.value)} />

        <input value={idade} placeholder="Idade" onChange={(event) => setIdade(event.target.value)} />
        <input value={pokemon} placeholder="Pokemon" onChange={(event) => setPokemon(event.target.value)} />

        <p>{errorInput}</p>
        <button type="submit" className='btn'>Registrar</button>

      </form>

        <ul className='ul'>
        {data.map((item, i) => (
          <li key={i}>{item }</li>
        ))}
      </ul>

    </>
  );
}

export default App;
