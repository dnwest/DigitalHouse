import HeroSlider from "../components/UI/HeroSlider";
import Helmet from "../components/Helmet/Helmet";
import { Container, Row, Col } from "reactstrap";
import ServicesList from "../components/UI/ServicesList";
import CategoryCard from "../components/UI/CategoryCard";
import api from "../services/api";
import { useState, useEffect } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import { Pagination, Navigation } from "swiper";
import "swiper/css/navigation";
import CategoryFinder from "../components/UI/CategoryFinder";
import "../styles/loading.css";

const Home = () => {
  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [categories, setCategories] = useState([]);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
  useEffect(() => {
    // Função assíncrona para buscar todas as categorias da localização do usuário
    const getAllCategoriesByLocation = async () => {
      try {
        const response = await api.get(
          "/category/all_in_location?location_id=7"
        );
        // console.log("response.data de getAllCategoriesByLocation(): ", response.data);
        setCategories(response.data);
      } catch (error) {
        // console.log(error);
        alert("Ocorreu um erro ao buscar todas as categorias.");
      }
    };

    getAllCategoriesByLocation();
  }, []);

  // Carregamento do loading
  if (categories.length === 0) {
    return <div className="loading">Loading&#8230;</div>;
  }

  return (
    <Helmet title="Home">
      {/*====== Search Form ======*/}
      <section className="p-0 hero__slider-section">
        <HeroSlider />

        <Container className="form_container">
          <Row className="form_row">
            <Col lg="12" md="12" sm="12">
              <CategoryFinder />
            </Col>
          </Row>
        </Container>
      </section>

      {/*====== Services ======*/}
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

      {/*====== Category List ======*/}
      <section>
        <Container>
          <Row>
            <Col lg="12" className="text-center mb-5">
              <h6 className="section_subtitle">Check it out our</h6>
              <h2 className="section_title">Hot Offers</h2>
            </Col>

            <Swiper
              slidesPerView={3}
              spaceBetween={10}
              pagination={{
                clickable: true,
              }}
              modules={[Pagination, Navigation]}
              className="mySwiper"
              navigation={true}
              breakpoints={{
                280: {
                  slidesPerView: 1,
                },
                768: {
                  slidesPerView: 2,
                  spaceBetween: 20,
                },
                1224: {
                  slidesPerView: 3,
                  spaceBetween: 20,
                },
              }}
            >
              {categories.map((category) => (
                <SwiperSlide key={category.id}>
                  <CategoryCard category={category} />
                </SwiperSlide>
              ))}
            </Swiper>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Home;
