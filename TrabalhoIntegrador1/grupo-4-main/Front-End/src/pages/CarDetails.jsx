
import Helmet from "../components/Helmet/Helmet";
import { Container, Row, Col } from "reactstrap";
// import carData from "../assets/data/carData";
import CarItem from "../components/UI/CarItem";
// import axios from "axios";
import api from "../services/api";
import CarDetailItem from "../components/UI/CarDetailItem";
import { useState, useEffect, React } from "react";
import { useLocation } from 'react-router-dom'

const CarDetails = () => {


  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [products, setProducts] = useState([]);
  const location  = useLocation()
  const { category_id } = location.state
  
  

  useEffect(() => {
    // Função assíncrona para buscar todos as categorias
    
    const getProducts = async () => {
      try {
        const response = await api.get("/category/id=" + category_id);
        console.log("response.data de product(): ", response.data.productList);
        setProducts(response.data.productList);
        // setIsLoading(false);
      } catch (error) {
        // console.log(error);
        alert("Ocorreu um erro ao buscar todos as categorias.");
      }
    };


    getProducts();
  }, []);

  if (products.length === 0) {

       // Carregamento do loading
    return <div class="loading">Loading&#8230;</div>;
  }

  console.log("Produtos encontrados: ()", products);

  return (
    <Helmet title="Home">
     
      

      {/*====== car list Section ======*/}
      <section>
        <Container>
          <Row>
            <Col lg="12" className="text-center mb-5">
            <h2 className="section_title">Select the best car </h2>
              <h6 className="section_subtitle">group and rate for you</h6>
              
            </Col>

            {
            products.map((product) => (
              <CarDetailItem key={product.id} product={product} />
            ))}
            
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default CarDetails;