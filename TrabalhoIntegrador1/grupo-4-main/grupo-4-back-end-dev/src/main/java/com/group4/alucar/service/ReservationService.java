package com.group4.alucar.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.alucar.dto.ReservationRequestDto;
import com.group4.alucar.enumeration.ReservationStatus;
import com.group4.alucar.model.CarEntity;
import com.group4.alucar.model.ReservationEntity;
import com.group4.alucar.repository.CarRepository;
import com.group4.alucar.repository.CategoryRepository;
import com.group4.alucar.repository.ClientRepository;
import com.group4.alucar.repository.LocationRepository;
import com.group4.alucar.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ClientRepository clientRepository;

    // public ReservationEntity save(ReservationEntity reservation) {
    //     return reservationRepository.save(reservation);
    // }

    public ReservationEntity save(ReservationRequestDto reservation) {
        ReservationEntity reservationEntity = ReservationEntity.builder()
            .client(clientRepository.getReferenceById(reservation.getClientId()))
            .category(categoryRepository.getReferenceById(reservation.getCategoryId()))
            .pickupLocation(locationRepository.getReferenceById(reservation.getPickupLocationId()))
            .returnLocation(locationRepository.getReferenceById(reservation.getReturnLocationId()))
            .pickupDatetime(reservation.getPickupDatetime())
            .returnDatetime(reservation.getReturnDatetime())
            .totalInvoiceAmount(calculateTotalInvoiceAmount(reservation))
            .reservationStatus(ReservationStatus.SCHEDULED)
            .build();

        return reservationRepository.save(reservationEntity);
    }

    public List<ReservationEntity> getAll() {
        return reservationRepository.findAll();
    }

    public Optional<ReservationEntity> getById(Long id) {
        return reservationRepository.findById(id);
    }

    // public ReservationEntity update(ReservationEntity reservation) {
    //     return reservationRepository.save(reservation);
    // }
    
    public ReservationEntity update(ReservationRequestDto reservation) {
        if (reservationRepository.existsById(reservation.getId())) {
            ReservationEntity reservationEntity = reservationRepository.findById(reservation.getId()).get();
            reservationEntity.setPickupDatetime(reservation.getPickupDatetime());
            reservationEntity.setReturnDatetime(reservation.getReturnDatetime());
            reservationEntity.setTotalInvoiceAmount(calculateTotalInvoiceAmount(reservation));
            reservationEntity.setReservationStatus(ReservationStatus.UPDATED);
            return reservationRepository.save(reservationEntity);
        }

        return null;
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<ReservationEntity> getReservationsByClient(Long clientId) {
        return reservationRepository.findReservationsByClient(clientId);
    }

    public List<ReservationEntity> getReservationsByDate(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findReservationsByDate(startDate, endDate);
    }

    public List<ReservationEntity> getReservationsByLocation(Long locationId) {
        return reservationRepository.findReservationsByLocation(locationId);
    }

    public List<ReservationEntity> getReservationsByCategory(Long categoryId) {
        return reservationRepository.findReservationsByCategory(categoryId);
    }

    public List<ReservationEntity> getReservationsByStatus(String status) {
        return reservationRepository.findReservationsByStatus(status);
    }

    public List<ReservationEntity> getReservationsByDateAndLocation(LocalDate startDate, LocalDate endDate, Long locationId) {
        return reservationRepository.findReservationsByDateAndLocation(startDate, endDate, locationId);
    }

    public Optional<ReservationEntity> cancelReservation(Long reservationId) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent()) {
            reservation.get().setReservationStatus(ReservationStatus.CANCELED);
            reservationRepository.save(reservation.get());
        }
        return reservation;
    }

    public Boolean bookCar(Long reservationId, CarEntity car) {
        if (reservationRepository.existsById(reservationId) && carRepository.existsById(car.getId())) {
            car.setIsAvailable(false);
            
            carRepository.save(car);
            
            ReservationEntity reservationEntity = ReservationEntity.builder()
                .id(reservationId)
                .car(car)
                .reservationStatus(ReservationStatus.TRAVELING)
                .build();

            reservationRepository.save(reservationEntity);
            
            return true;
        }

        return false;
    }

    public BigDecimal calculateTotalInvoiceAmount(ReservationRequestDto reservation) {
        BigDecimal categoryPrice = categoryRepository.getCategoryPrice(reservation.getCategoryId());
        Long numberOfDays = ChronoUnit.DAYS.between(reservation.getPickupDatetime(), reservation.getReturnDatetime());
        return categoryPrice.multiply(BigDecimal.valueOf(numberOfDays)).setScale(2, RoundingMode.HALF_EVEN);
    }
}
