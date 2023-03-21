import { useState, useEffect } from "react"
import { Col } from "reactstrap";
import { Link } from "react-router-dom";
import "../../styles/car-item.css";
import 'bootstrap/dist/css/bootstrap.min.css';









const CarDetailItem = ({ product }) => {
  const { id, model, brand, imageList } = product;


  return (


    <Col lg="4" md="4" sm="6" className="mb-5">


      <div className="car_item">
        <div className="car_item-content mt-4" >

          <img class="img_produto" alt="teste" src={`${imageList[0].url}`}></img>
          <h4 className="section_title text-center">Modelo: {model}</h4>
          <h6 className="rent_price text-center">
            Marca: {brand}
          </h6>
          <div className="car_item-info d-flex align-items-center justify-content-between mt-3 mb-4">
            <span className="d-flex align-items-center gap-1">
              <i class="ri-setting-2-line"></i> {brand}
            </span>
          </div>

          <button className="car_item-btn car_btn-rent w-50">

            <Link to="/calendar" state={{ product_id: `${id}` }}> Select</Link>
          </button>


          <button className="car_item-btn car_btn-details w-50">


            <div >
              <a id="dLabel" data-target="#" role="button" aria-haspopup="true" aria-expanded="false">
                Details
                <span class="caret"></span>
              </a>

              <ul class="dropdown-menu" aria-labelledby="dLabel">
                ...
              </ul>
            </div>

            {/* <Link to="/teste">Details</Link> */}
          </button>


        </div>
      </div>
    </Col>
  );
};

export default CarDetailItem;