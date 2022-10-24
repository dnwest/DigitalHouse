import ButtonApp from "./Button";

const App =() => 
<>
<h1>Hello World</h1>

<ButtonApp name="Pressione" color="red" btn={botaoPressione}/>
<ButtonApp name="Click" color="yellow"/>
<ButtonApp name="Press" color="blue" btn={botaoPress}/>
<ButtonApp name="LogIn" color="green"/>
</>

function botaoPressione(){
  alert('Hello')
}

function botaoPress(){
  alert('World')
}
export default App;