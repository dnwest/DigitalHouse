package com.group4.alucar.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.group4.alucar.enumeration.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @NotNull(message = "The pickupDatetime field cannot be null.")
    @Future(message = "The pickupDatetime field must be a date in the future.")
    private LocalDateTime pickupDatetime;

    @NotNull(message = "The returnDatetime field cannot be null.")
    @Future(message = "The returnDatetime field must be a time in the future.")
    private LocalDateTime returnDatetime;

    @NotNull(message = "The totalInvoiceAmount field cannot be null.")
    @DecimalMin(value = "0.0", inclusive = true, message = "The totalInvoiceValue field must be a number between 0.0 and 9999999.99.")
    @Digits(integer = 7, fraction = 2, message = "The totalInvoiceValue field must be a number between 0.0 and 9999999.99.")
    private BigDecimal totalInvoiceAmount;

    @ManyToOne
    @JoinColumn(name = "pickup_location_id")
    private LocationEntity pickupLocation;

    @ManyToOne
    @JoinColumn(name = "return_location_id")
    private LocationEntity returnLocation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;
}
