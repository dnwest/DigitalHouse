import Helmet from "../components/Helmet/Helmet";
import { Container } from "reactstrap";
import CategoryCard from "../components/UI/CategoryCard";
import api from "../services/api";
import React, { useState, useEffect } from "react";
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";
import { useNavigate, useLocation } from "react-router-dom";

const AvailableCategories = () => {
  // useLocation retorna o objeto de localização atual
  const location = useLocation();
  const searchParameters = location.state?.searchParameters;
  console.log("searchParameters: ", searchParameters);

  const navigate = useNavigate();

  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [categories, setCategories] = useState([]);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
  useEffect(() => {
    // Função assíncrona para buscar as categorias disponíveis para a localização solicitada
    const getAvailableCategoriesByLocation = async (pickupLocationId) => {
      try {
        const response = await api.get(
          `/category/available_in_location?location_id=${pickupLocationId}`
        );
        // console.log("response.data de getAvailableCategoriesByLocation(): ", response.data);
        setCategories(response.data);
      } catch (error) {
        // console.log("error: ", error);
        alert("Ocorreu um erro ao buscar as categorias.");
      }
    };

    if (searchParameters === undefined) {
      navigate("/home");
    } else {
      getAvailableCategoriesByLocation(searchParameters.pickupLocation.id);
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [searchParameters]);

  // Carregamento do loading
  if (categories.length === 0) {
    return <div className="loading">Loading&#8230;</div>;
  }

  return (
    <Helmet title="Available Categories">
      {/*====== Category List ======*/}
      <section>
        <Container>
          {/* <div className="title-listing"> */}
          <h2 className="section_subtitle">Select the best car</h2>
          <h6 className="section_title">group and rate for you</h6>
          {/* </div> */}

          <div className="wrapper">
            {/* Swiper
              slidesPerView={3}
              spaceBetween={30}
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
                  spaceBetween: 20
                },
                1224: {
                  slidesPerView: 3,
                  spaceBetween: 20,
                }
              }}
            > */}

            {categories.map((category) => (
              <CategoryCard key={category.id} category={category} />
            ))}
          </div>
        </Container>
      </section>
    </Helmet>
  );
};

export default AvailableCategories;
