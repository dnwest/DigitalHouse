import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import { Icon } from "@iconify/react";
import "../../styles/category-features.css";
import ButtonGroup from "react-bootstrap/ButtonGroup";

function CategoryFeatures({ features }) {
  return (
    // <DropdownButton
    //   variant="custom"
    //   id="dropdown-basic-button"
    //   title="Details"
    //   drop="up"
    // >
    //   {features.map((feature) => (
    //     <Dropdown.Item className="d-inline-block features" key={feature.id}>
    //       {<Icon key={feature.id} icon={feature.icon} />} {feature.description}
    //     </Dropdown.Item>
    //   ))}
    // </DropdownButton>
    <Dropdown drop="up">
      <Dropdown.Toggle className="w-100" variant="secondary">Details</Dropdown.Toggle>
      <Dropdown.Menu className="w-100 features">
        {features.map((feature) => (
          <Dropdown.Item key={feature.id}>
            {<Icon key={feature.id} icon={feature.icon} />}{" "}
            {feature.description}
          </Dropdown.Item>
        ))}
      </Dropdown.Menu>
    </Dropdown>
  );
}

export default CategoryFeatures;
