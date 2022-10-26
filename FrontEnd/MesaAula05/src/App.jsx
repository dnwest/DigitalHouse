import app from './App.css'

const produtos = [
  {
    id: 1,
    nome: "Macbook Air 13 Chip M1 256gb",
    image: "https://m.media-amazon.com/images/I/41O807iqbCL._AC_SX522_.jpg",
    preco: "R$ 7999.00",
  },
  {
    id: 2,
    nome: "Echo Dot (4ª Geração)",
    image: "https://m.media-amazon.com/images/I/714Rq4k05UL._AC_SL1000_.jpg",
    preco: "R$ 379.00",
  },
  {
    id: 3,
    nome: "Câmera Ip Sem Fio 360°",
    image: "https://m.media-amazon.com/images/I/51F70OM213S._AC_SL1000_.jpg",
    preco: "R$ 199.00",
  },
  {
    id: 4,
    nome: "Fechadura Eletrônica Digital Inteligente Zigbee",
    image: "https://m.media-amazon.com/images/I/51RXeqMLceL._AC_SL1500_.jpg",
    preco: "R$ 1599.00",
  },
];

function App() {
  return (
    <div className='container'>
      <h1>Produtos</h1>

      <ul>
        {
          produtos.map(item => {
            return(
              <li key={item.id}>
                <h2>{item.nome}</h2>
                <img className='image' src={item.image} alt="" />
                <h3>{item.preco}</h3>
              </li>
            )
          })
        }
      </ul>
    </div>
  );
}

export default App;
