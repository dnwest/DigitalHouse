
import Helmet from "../components/Helmet/Helmet";
import { Container, Row, Col } from "reactstrap";
import CarItem from "../components/UI/CarItem";
import api from "../services/api";
import { useState, useEffect, React } from "react";

const CarListing = () => {


  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    // Função assíncrona para buscar todos as categorias
    const getAllCategories = async () => {
      try {
        const response = await api.get("/category");
        console.log("response.data de getAllCategories(): ", response.data);
        setCategories(response.data);
        // setIsLoading(false);
      } catch (error) {
        // console.log(error);
        alert("Ocorreu um erro ao buscar todos as categorias.");
      }
    };

    getAllCategories();
  }, []);

  if (categories.length === 0) {

       // Carregamento do loading
    return <div class="loading">Loading&#8230;</div>;
  }

  return (
    <Helmet title="Home">
     
      

      {/*====== car list Section ======*/}
      <section>
        <Container>
          <Row>
            <Col lg="12" className="text-center mb-5">
              <h6 className="section_subtitle">Check it out our</h6>
              <h2 className="section_title">Hot Offers</h2>
            </Col>

            {categories.map((category) => (
              <CarItem key={category.id} category={category} />
            ))}
            
          </Row>
        </Container>
      </section>

    </Helmet>

    
  );
};

export default CarListing;