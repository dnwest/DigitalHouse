// package com.group4.alucar.dto;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit;
// import java.util.ArrayList;
// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.group4.alucar.model.LocationEntity;
// import com.group4.alucar.model.ReservationEntity;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// @JsonIgnoreProperties(ignoreUnknown = true)
// public class ReservationResponseDto {
//     private Long clientId;
//     private String clientName;
//     private Long reservationId;
//     private LocalDateTime pickupDatetime;
//     private LocalDateTime returnDatetime;
//     private LocationEntity pickupLocation;
//     private LocationEntity returnLocation;
//     private String reservationStatus;
//     private Long categoryId;
//     private String categoryName;
//     private Long numberOfDays;
//     private BigDecimal dailyRate;
//     private BigDecimal totalInvoiceAmount;

//     public static List<ReservationResponseDto> fromReservationEntities(List<ReservationEntity> reservationEntities) {
//         List<ReservationResponseDto> reservations = new ArrayList<>();
//         reservationEntities.forEach(reservation -> {
//             reservations.add(ReservationResponseDto.builder()
//                 .clientId(reservation.getClient().getId())
//                 .clientName(reservation.getClient().getFullName())
//                 .reservationId(reservation.getId())
//                 .pickupDatetime(reservation.getPickupDatetime())
//                 .returnDatetime(reservation.getReturnDatetime())
//                 .pickupLocation(reservation.getPickupLocation())
//                 .returnLocation(reservation.getReturnLocation())
//                 .reservationStatus(reservation.getReservationStatus().toString())
//                 .categoryId(reservation.getCategory().getId())
//                 .categoryName(reservation.getCategory().getName())
//                 .numberOfDays(ChronoUnit.DAYS.between(reservation.getPickupDatetime(), reservation.getReturnDatetime()))
//                 .dailyRate(reservation.getCategory().getPrice())
//                 .totalInvoiceAmount(reservation.getTotalInvoiceAmount())
//                 .build());
//         });
//         return reservations;
//     }
// }

package com.group4.alucar.dto;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.alucar.model.ReservationEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationResponseDto {
    private Long clientId;
    private String clientName;
    private List<Map<String, Object>> reservations;

    public static ReservationResponseDto fromReservationEntities(List<ReservationEntity> reservationEntities) {
        List<Map<String, Object>> reservations = new ArrayList<>();

        reservationEntities.forEach(reservation -> {
            Map<String, Object> reservationMap = new LinkedHashMap<>();
            reservationMap.put("reservationId", reservation.getId());
            reservationMap.put("pickupDatetime", reservation.getPickupDatetime());
            reservationMap.put("returnDatetime", reservation.getReturnDatetime());
            reservationMap.put("pickupLocation", reservation.getPickupLocation());
            reservationMap.put("returnLocation", reservation.getReturnLocation());
            reservationMap.put("reservationStatus", reservation.getReservationStatus());
            reservationMap.put("categoryId", reservation.getCategory().getId());
            reservationMap.put("categoryName", reservation.getCategory().getName());
            reservationMap.put("numberOfDays", ChronoUnit.DAYS.between(reservation.getPickupDatetime(), reservation.getReturnDatetime()));
            reservationMap.put("dailyRate", reservation.getCategory().getPrice());
            reservationMap.put("totalInvoiceAmount", reservation.getTotalInvoiceAmount());

            reservations.add(reservationMap);
        });

        return ReservationResponseDto.builder()
            .clientId(reservationEntities.get(0).getClient().getId())
            .clientName(reservationEntities.get(0).getClient().getFullName())
            .reservations(reservations)
            .build();
    }
}
