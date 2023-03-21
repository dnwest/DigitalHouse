import Helmet from "../components/Helmet/Helmet";
import { Container, Row, Col } from "reactstrap";
import FindCarForm from "../components/UI/FindCarFormReserve";
import { Box,Flex, Spacer } from '@chakra-ui/react'
import CarItem from "../components/UI/CarItem";
import CarDetailItemReserve from "../components/UI/CarDetailItemReserve";
import { useState, useEffect, React } from "react";
import { useLocation } from 'react-router-dom';
import api from "../services/api";





const Calendar = () => {

  const [product, setProducts] = useState([]);
  const location  = useLocation()
  const { product_id } = location.state

  useEffect(() => {
    // Função assíncrona para buscar todos as categorias
    
    const getProducts = async () => {
      try {
        const response = await api.get("/product/id=" + product_id);
        console.log("response.data de product-calendar(): ", response.data);
        setProducts(response.data);
        // setIsLoading(false);
      } catch (error) {
        // console.log(error);
        alert("Ocorreu um erro ao buscar todos as categorias.");
      }
    };


    getProducts();
  }, []);

  if (product.length === 0) {
    return <div class="loading">Loading&#8230;</div>;
  }


  return (
    <Helmet title="Home">
      <section className="p-0 hero__slider-section">



        <Container>
          <Row className="form_row">

            <Col lg="12" md="12" sm="12">
              <FindCarForm />
            </Col>

          </Row>
          <CarDetailItemReserve key={product.id} product={product} />
        </Container>

           




      </section>


    </Helmet>
  );
};




export default Calendar;



