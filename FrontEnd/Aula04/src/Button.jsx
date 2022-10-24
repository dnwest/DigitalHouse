function ButtonApp(props){

    return <button onClick={props.btn} style={{backgroundColor:props.color}}>{props.name}</button>
}

export default ButtonApp;