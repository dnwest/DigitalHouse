import React from "react";
import { Link } from "react-router-dom";
import "../../styles/categories.css";
import { useLocation } from "react-router-dom";
import CategoryFeatures from "./CategoryFeatures";
import Carousel from "react-bootstrap/Carousel";

const CategoryCard = ({ category }) => {
  const { id, name, description, price, images, features } = category;

  // useLocation retorna o objeto de localização atual
  const location = useLocation();

  return (
    <div className="card-container-listing">
      <Carousel fade>
        {images.map((image) => (
          <Carousel.Item key={image.id}>
            <img
              className="d-block w-100 img-card"
              src={image.url}
              alt={image.description}
            />
          </Carousel.Item>
        ))}
      </Carousel>
      <div className="card-content">
        <span className="car-name">{name}</span>
        <span className="card-price">U$ {price} </span>
        <div>
          <span>{description}</span>
        </div>
      </div>

      <div>
        {location.pathname === "/reservation/step-1" ? (
          <button className="car_item-btn car_btn-rent">
            <Link
              to={"../step-2"}
              state={{
                searchParameters: location.state?.searchParameters,
                category: category,
              }}
            >
              Book Now
            </Link>
          </button>
        ) : null}

        {/* <button className="car_item-btn car_btn-details w-50">
          <Link to={"/category_details"}>Details</Link>
        </button> */}
        <CategoryFeatures features={features} />
      </div>
    </div>
  );
};

export default CategoryCard;
