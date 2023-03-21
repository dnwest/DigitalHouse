import React from "react";
import { Col } from "reactstrap";
import "../../styles/services-list.css";
import ServicesData from "../../assets/data/serviceData";

function ServicesList() {
  return (
    <>
      {ServicesData.map((item) => (
        <ServiceItem item={item} key={item.id} />
      ))}
    </>
  );
}

const ServiceItem = ({ item }) => (
  <Col lg="4" md="4" sm="6" className="mb-3">
    <div className="service_item">
      <div className="service_icon_title">
        <span className="mb-3 d-inline-block">
          <i class={item.icon} />
        </span>
        <h6>{item.title}</h6>
      </div>
      <p className="section_description">{item.desc}</p>
    </div>
  </Col>
);

export default ServicesList;
