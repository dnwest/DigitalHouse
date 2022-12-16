import { useState, useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import ScheduleFormModal from "../../Components/ScheduleFormModal";
import api from "../../services/api";
import styles from "./DetailCard.module.css";
import { NavBarContext } from "../../Components/NavBarContext";

const DetailCard = () => {
  const { contextIsLight } = useContext(NavBarContext);

  const { matricula } = useParams();
  const [dentista, setDentista] = useState([]);

  const usuario = [];

  async function getDentistaById() {
    try {
      const response = await api.get(`/dentista?matricula=${matricula}`);
      setDentista(response.data);

      usuario.push(dentista.usuario);
    } catch (error) {
      alert.error("Error" + error);
    }
  }

  useEffect(() => {
    getDentistaById();
  }, []);

  let name = "";

  for (const key in dentista) {
    name = dentista[key].username;
  }
  return (
    <>
      <h1>Detalhes sobre o Dentista {dentista.nome} </h1>
      <section className="card col-sm-12 col-lg-6 container">
        <div
          className={`card-body row ${
            contextIsLight ? styles.card : styles.cardDark
          }`}
        >
          <div className="col-sm-12 col-lg-6">
            <img
              className="card-img-top"
              src="/images/doctor.jpg"
              alt="doctor placeholder"
            />
          </div>
          <div className="col-sm-12 col-lg-6">
            <ul className="list-group">
              <li className="list-group-item">Nome: {dentista.nome}</li>
              <li className="list-group-item">
                Sobrenome: {dentista.sobrenome}
              </li>
              <li className="list-group-item">Usuário: {name}</li>
            </ul>
            <div className="text-center">
              <button
                data-bs-toggle="modal"
                data-bs-target="#exampleModal"
                className={
                  contextIsLight
                    ? `btn btn-light ${styles.button}`
                    : `btn btn-dark ${styles.button}`
                }
              >
                Marcar consulta
              </button>
            </div>
          </div>
        </div>
      </section>
      <ScheduleFormModal />
    </>
  );
};

export default DetailCard;
