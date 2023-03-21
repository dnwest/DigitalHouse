import React from "react";
import { Col } from "reactstrap";
import { Link } from "react-router-dom";
import "../../styles/car-item.css";




const CarItem = ({ category }) => {
  const { name, description, price, id, productList } = category;

  return (
    <Col lg="4" md="4" sm="6" className="mb-5">
      <div className="car_item">
        <div className="car_item-content mt-4">
          <img class="img_produto" src={`${productList[0].imageList[0].url}`} alt="alt" />

          <div>
            <h4 className="section_title text-center">{name}</h4>
            <h6 className="rent_price text-center">
              U$ {price}.00 <span>/ Day</span>
            </h6>
            <div className="car_item-info d-flex align-items-center justify-content-between mt-3 mb-4">
              <span className="d-flex align-items-center gap-1">
                <i class="ri-setting-2-line"></i> {description}
              </span>
            </div>
          </div>

          <div>
            <button className="car_item-btn car_btn-rent w-50  ">
              <Link to={`/cars/${name}`} state={{ category_id: `${id}` }}> Rent</Link>
            </button>

            <button className="car_item-btn car_btn-details w-50">
              <Link to={`/cars/${name}`}>Details</Link>
            </button>
          </div>

        </div>
      </div>
    </Col>
  );
};

export default CarItem;
