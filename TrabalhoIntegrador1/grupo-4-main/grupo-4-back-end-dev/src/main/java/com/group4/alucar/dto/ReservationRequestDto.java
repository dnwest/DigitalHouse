package com.group4.alucar.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationRequestDto {
    private Long id;
    private Long clientId;
    private Long categoryId;
    private Long pickupLocationId;
    private Long returnLocationId;
    private LocalDateTime pickupDatetime;
    private LocalDateTime returnDatetime;
}
