import { useState, useEffect } from "react"
import { Col } from "reactstrap";
import { Link } from "react-router-dom";
import "../../styles/car-item-reserve.css";
import 'bootstrap/dist/css/bootstrap.min.css';





const CarDetailItemReserve = ({ product }) => {
    const { id, model, brand, imageList } = product;

    console.log("Id do carro escolhido: " + id)

    return (

        <Col lg="4" md="4" sm="6" className="mb-5">
            <div class="content-reserve">

                <div className="car_item-reserve">
                    <div className="car_item-content mt-4" >

                        <img class="img_produto-reserve" alt="teste" src={`${imageList[0].url}`}></img>
                        <h4 className="section_title text-center">Modelo: {model}</h4>
                        <h6 className="rent_price text-center">
                            Marca: {brand}
                        </h6>
                        <div className="car_item-info-reserve d-flex align-items-center justify-content-between mt-3 mb-4">
                            <span className="d-flex align-items-center gap-1">
                                <i class="ri-setting-2-line"></i> {brand}
                            </span>
                        </div>
                        <div>

                        </div>

                        <div className="info-btn">
                            <button className="car_item-btn car_btn-rent w-50">

                                <Link to="/calendar"> Select</Link>
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

                            </button>
                        </div>


                    </div>

                    <div>
                        <div className="car_item-content mt-4" >

                            <h4 className="section_title text-center">Reserva</h4>
                            <h6 className="rent_price text-center">
                                Retirada: {brand}
                            </h6>

                            <h6 className="d-flex align-items-center gap-1">
                                Devolução: {brand}
                            </h6>

                        </div>

                    </div>


                </div>
            </div>


        </Col>
    );
};

export default CarDetailItemReserve;