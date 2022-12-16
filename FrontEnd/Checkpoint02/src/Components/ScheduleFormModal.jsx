import ScheduleForm from '../Components/ScheduleForm';
import styles from "./ScheduleFormModal.module.css";
import { NavBarContext } from '../Components/NavBarContext';
import { useContext } from 'react';

const ScheduleFormModal = () => {

  const { contextIsLight } = useContext(NavBarContext);

  return (
    <div className={`modal fade`} id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div className="modal-dialog">
        <div className={`modal-content ${contextIsLight ? styles.Modal : styles.DarkModal}`}>
          <div className="modal-header">
            <h1 className={`modal-title fs-5 ${contextIsLight ? 'text-dark' : 'text-light'}`} id="exampleModalLabel">Selecione o dentista, paciente e a data e hora</h1>
            <button type="button" className={`btn-close ${contextIsLight ? styles.closeButton : styles.closeButtonDark}`} data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div className="modal-body">
            <ScheduleForm />
          </div>
        </div>
      </div>
    </div >

  );
};

export default ScheduleFormModal;