
import HeroSlider from "../components/UI/HeroSlider";
import Helmet from "../components/Helmet/Helmet";
import { Container, Row, Col } from "reactstrap";
import FindCarForm from "../components/UI/FindCarForm";
import AboutSection from "../components/UI/AboutSection";
import ServicesList from "../components/UI/ServicesList";
import CarItem from "../components/UI/CarItem";
import api from "../services/api";
import React, { useState, useEffect } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import { Pagination , Navigation} from "swiper";
 import styles from '../styles/loading.modulo.css';





const Home = () => {

 


  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [categories, setCategories] = useState([]);
  // const [isLoading, setIsLoading] = useState(true);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
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
   // Carregamento do loading
  if (categories.length === 0) {
    return <div class="loading">Loading&#8230;</div>
  }

  return (
    <Helmet title="Home">
      <section className="p-0 hero__slider-section">
        <HeroSlider />

        <div className="hero_form">
          <Container>
            <Row className="form_row">
              {

              }
              <Col lg="12" md="12" sm="12">
                <FindCarForm />
              </Col>
            </Row>
          </Container>
        </div>
      </section>
      {/*====== About Section ======*/}
      <AboutSection />
      {/*====== Services Section ======*/}
      <section>
        <Container>
          <Row>
            <Col lg="12" className="mb-5 text-center">
              <h6 className="section_subtitle">See our</h6>
              <h2 className="section_title">Popular Services</h2>
            </Col>
            <ServicesList />
          </Row>
        </Container>
      </section>

      {/*====== car list Section ======*/}

      <section>
        <Container>
          <Row>
            <Col lg="12" className="text-center mb-5">
              <h6 className="section_subtitle">Check it out our</h6>
              <h2 className="section_title">Hot Offers</h2>
            </Col>

            <Swiper
              slidesPerView={3}
              spaceBetween={30}
              pagination={{
                clickable: true,
              }}
              modules={[Pagination, Navigation]}
              className="mySwiper"
                navigation={true}
            >

              {categories.map((category) => (

                <SwiperSlide><CarItem key={category.id} category={category} /></SwiperSlide>

              ))}

            </Swiper>




          </Row>


        </Container>
      </section>
    </Helmet>
  );
};

export default Home;
