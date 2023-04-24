import Helmet from "../components/Helmet/Helmet";
import { Container } from "reactstrap";
import CategoryCard from "../components/UI/CategoryCard";
import api from "../services/api";
import { useState, useEffect } from "react";
import "swiper/css/navigation";
// import { Pagination, Navigation } from "swiper";
import "../styles/loading.css";

const Categories = () => {
  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [categories, setCategories] = useState([]);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
  useEffect(() => {
    // Função assíncrona para buscar todas as categorias da localização do usuário
    const getAllCategories = async () => {
      try {
        const response = await api.get("/category");
        // console.log("response.data de getAllCategories(): ", response.data);
        setCategories(response.data);
      } catch (error) {
        // console.log(error);
        alert("Ocorreu um erro ao buscar todas as categorias.");
      }
    };

    getAllCategories();
  }, []);

  // Carregamento do loading
  if (categories.length === 0) {
    return <div className="loading">Loading&#8230;</div>;
  }

  return (
    <Helmet title="Categories">
      {/*====== Category List ======*/}
      <section>
        <Container>
          <div className="title-categories">
            <h2 className="section_subtitle">Select the best car</h2>
            <h6 className="section_title">group and rate for you</h6>
          </div>

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

export default Categories;
