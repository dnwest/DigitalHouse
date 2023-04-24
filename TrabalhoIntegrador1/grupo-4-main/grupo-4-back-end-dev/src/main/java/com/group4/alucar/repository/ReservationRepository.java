package com.group4.alucar.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group4.alucar.model.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    @Query(
        value = """
            SELECT * 
            FROM reservation 
            WHERE reservation.client_id = ?1
            """, 
        nativeQuery = true
    )
    List<ReservationEntity> findReservationsByClient(Long clientId);

    @Query(
        value = """
            SELECT * 
            FROM reservation 
            WHERE reservation.pickup_datetime BETWEEN ?1 AND ?2
            """, 
        nativeQuery = true
    )
    List<ReservationEntity> findReservationsByDate(LocalDate startDate, LocalDate endDate);

    @Query(
        value = """
            SELECT * 
            FROM reservation 
            WHERE reservation.pickup_location_id = ?1
            """, 
        nativeQuery = true
    )
    List<ReservationEntity> findReservationsByLocation(Long locationId);

    @Query(
        value = """
            SELECT * 
            FROM reservation 
            WHERE reservation.category_id = ?1
            """, 
        nativeQuery = true
    )
    List<ReservationEntity> findReservationsByCategory(Long categoryId);

    @Query(
        value = """
            SELECT * 
            FROM reservation 
            WHERE reservation.reservation_status = ?1
        """, 
        nativeQuery = true
    )
    List<ReservationEntity> findReservationsByStatus(String status);

    @Query(
        value = """
            SELECT * 
            FROM reservation 
            WHERE reservation.pickup_datetime BETWEEN ?1 AND ?2 
            AND reservation.pickup_location_id = ?3
        """, 
        nativeQuery = true
    )
    List<ReservationEntity> findReservationsByDateAndLocation(LocalDate startDate, LocalDate endDate, Long locationId);
}
