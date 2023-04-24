// import Table from "react-bootstrap/Table";
import { useContext, useState, useEffect } from "react";
import api from "../../services/api";
import { LoginSessionContext } from "../../contexts/LoginSessionContext";
import "../../styles/reservationHistory.css";
import { Icon } from "@iconify/react";
import moment from "moment";
import { Alert } from "react-bootstrap";
import { useLocation } from "react-router-dom";

const ReservationHistory = () => {
  const { loginSession } = useContext(LoginSessionContext);

  const initialState = {
    clientId: "",
    clientName: "",
    reservations: [],
  };

  // useState retorna um par de valores: o estado atual e uma função que atualiza o estado
  const [historic, setHistoric] = useState(initialState);
  const [isLoading, setIsLoading] = useState(true);

  const location = useLocation();
  const [reservation, setReservation] = useState(location.state?.reservation);

  // useEffect permite executar efeitos colaterais (será executada apenas 1x após a primeira renderização do componente)
  useEffect(() => {
    // Função assíncrona para buscar o histórico de reservas do usuário
    const getReservationHistory = async (token) => {
      try {
        const configuration = {
          headers: {
            Authorization: token,
          },
        };
        const response = await api.get(
          `/reservation/history?client_id=${loginSession.user.id}`,
          configuration
        );
        console.log(
          "response.data de getReservationHistory(): ",
          response.data
        );
        setHistoric(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log("error: ", error);
        if (error.response.data.status === 404) {
          console.log("RETORNOU 404");
          setIsLoading(false);
          return;
        }
        alert("Ocorreu um erro ao buscar o histórico de reservas do cliente.");
      }
    };

    getReservationHistory(loginSession.token);
  }, [loginSession.user.id, loginSession.token, reservation]);

  // const fillTableRow = (reservation) => {
  //   const { categoryId, ...bookings } = reservation;
  //   // console.log("bookings: ", bookings);

  //   bookings["pickupLocation"] = bookings.pickupLocation.city;
  //   bookings["returnLocation"] = bookings.returnLocation.city;

  //   return (
  //     <tr key={reservation.reservationId}>
  //       {Object.keys(bookings).map((key) => (
  //         <td key={key}>{bookings[key]}</td>
  //       ))}
  //     </tr>
  //   );
  // };

  // Carregamento do loading
  if (isLoading) {
    return <div className="loading">Loading&#8230;</div>;
  }

  // Função assíncrona para cancelar a reserva
  const cancelReservation = async (token, reservationId, index) => {
    try {
      const configuration = {
        method: "patch",
        maxBodyLength: Infinity,
        url: `/reservation/cancel/${reservationId}`,
        headers: {
          Authorization: token,
        },
      };
      const response = await api.request(configuration);
      console.log("response.data de cancelReservation(): ", response.data);
      // setHistoric((historic) => {
      //   const updatedReservations = [...historic.reservations];
      //   updatedReservations[index] = response.data;
      //   historic.reservations = updatedReservations
      //   return historic;
      // });
      const updatedReservations = [...historic.reservations];
      updatedReservations[index] = response.data.reservations[0];
      setHistoric({ ...historic, reservations: updatedReservations });
      alert("Reserva cancelada com sucesso!");
    } catch (error) {
      console.log(error);
      alert(
        "Ocorreu um erro ao cancelar a reserva, entre em contato com o suporte."
      );
    }
  };

  const handleClick = (reservationId, index) => {
    cancelReservation(loginSession.token, reservationId, index);
  };

  return (
    <div className="reservation-history">
      <h1>Hello {loginSession.user.fullName.split(" ")[0]}!</h1>
      <h2>Here is your booking history</h2>
      <ul className="main_card_history">
        {historic.reservations.length !== 0 ? (
          historic.reservations.map((reservation, index) => (
            <li key={reservation.reservationId} className="main_card_history">
              <div className="card_history">
                <h3 className="card_history_title">Reservation</h3>

                <p>{reservation.categoryName}</p>

                <h5>Pick Up</h5>
                <p>
                  {reservation.pickupLocation.city} -{" "}
                  {reservation.pickupLocation.state}
                </p>
                <p>
                  {moment(reservation.pickupDatetime).format(
                    "DD [de] MMMM [de] YYYY [às] HH:mm"
                  )}
                </p>

                <h5>Return</h5>
                <p>
                  {reservation.returnLocation.city} -{" "}
                  {reservation.returnLocation.state}
                </p>
                <p>
                  {moment(reservation.returnDatetime).format(
                    "DD [de] MMMM [de] YYYY [às] HH:mm"
                  )}
                </p>

                <h5>Details</h5>
                <p>Number of days: {reservation.numberOfDays}</p>
                <p>
                  Invoice amount:{" "}
                  {new Intl.NumberFormat("en-US", {
                    style: "currency",
                    currency: "USD",
                  }).format(reservation.totalInvoiceAmount)}
                </p>
                <p>Status: {reservation.reservationStatus}</p>
                {reservation.reservationStatus === "SCHEDULED" ||
                  reservation.reservationStatus === "UPDATED" ? (
                  <button
                    onClick={() =>
                      handleClick(reservation.reservationId, index)
                    }
                    className="card_history_footer"
                  >
                    <Icon
                      icon="ph:x-bold"
                      color="red"
                      className="card_history_footer_icon"
                    />
                    <p>Cancel</p>
                  </button>
                ) : null}
              </div>
            </li>
          ))
        ) : (
          <Alert variant="success">Você ainda não fez uma reserva.</Alert>
        )}
      </ul>
    </div>
  );
};

export default ReservationHistory;
