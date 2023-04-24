package com.group4.alucar.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.alucar.dto.ReservationRequestDto;
import com.group4.alucar.dto.ReservationResponseDto;
import com.group4.alucar.exception.handler.BadRequestException;
import com.group4.alucar.exception.handler.ResourceNotFoundException;
import com.group4.alucar.model.CarEntity;
import com.group4.alucar.model.ReservationEntity;
import com.group4.alucar.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/reservation")
@Tag(name = "Reservation", description = "Endpoints for managing reservation")
public class ReservationController {
    @Autowired
    ReservationService service;

    @PostMapping
    @Operation(
        summary = "Create a reservation",
        description = "Create a reservation passing a JSON representation",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationEntity.class))
            ),
            @ApiResponse(
                description = "Bad request",
                responseCode = "400",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<ReservationEntity> save(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody ReservationRequestDto reservation) throws BadRequestException {
        try {
            if (reservation.getReturnDatetime().isBefore(reservation.getPickupDatetime())) {
                throw new BadRequestException("The return date must be greater than the pickup date.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservation));
        } catch (Exception e) {
            throw new BadRequestException("Reservation properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping
    @Operation(
        summary = "Find all reservations",
        description = "Find all reservations",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ReservationEntity.class))
                    )
                }
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<List<ReservationEntity>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find a reservation",
        description = "Find a reservation by its ID",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationEntity.class))
            ),
            @ApiResponse(
                description = "Resource not found",
                responseCode = "404",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<ReservationEntity> getById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(service.getById(id).get());
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    @PutMapping
    @Operation(
        summary = "Update a reservation",
        description = "Update a reservation passing a JSON representation",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationEntity.class))
            ),
            @ApiResponse(
                description = "Bad request",
                responseCode = "400",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<ReservationEntity> update(@RequestBody ReservationRequestDto reservation) throws BadRequestException {
        try {
            if (reservation.getReturnDatetime().isBefore(reservation.getPickupDatetime())) {
                throw new BadRequestException("The return date must be greater than the pickup date.");
            }

            ReservationEntity reservationEntity = service.update(reservation);

            if (reservationEntity == null) {
                throw new ResourceNotFoundException(String.format("Reservation with ID = %s not found.", reservation.getId()));
            }

            return ResponseEntity.ok().body(reservationEntity);
        } catch (Exception e){
            throw new BadRequestException("Reservation properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a reservation",
        description = "Delete a reservation by its ID",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "No Content",
                responseCode = "204",
                content = @Content
            ),
            @ApiResponse(
                description = "Resource not found",
                responseCode = "404",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    // @GetMapping("/from_client")
    // @Operation(
    //     summary = "Find all reservations by client ID",
    //     description = "Find all reservations by client ID",
    //     tags = {"Reservation"},
    //     responses = {
    //         @ApiResponse(
    //             description = "Success",
    //             responseCode = "200",
    //             content = {
    //                 @Content(
    //                     mediaType = "application/json",
    //                     array = @ArraySchema(schema = @Schema(implementation = ReservationEntity.class))
    //                 )
    //             }
    //         ),
    //         @ApiResponse(
    //             description = "Internal server error",
    //             responseCode = "500",
    //             content = @Content
    //         )
    //     }
    // )
    // public ResponseEntity<List<ReservationEntity>> getReservationsByClient(@RequestParam(value = "client_id") Long clientId) throws Exception {
    //     try {
    //         return ResponseEntity.ok().body(service.getReservationsByClient(clientId));
    //     } catch (Exception e) {
    //         throw new Exception(e.getMessage());
    //     }
    // }

    @GetMapping("/on_date")
    @Operation(
        summary = "Find all reservations between two dates",
        description = "Find all reservations between two dates",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ReservationEntity.class))
                    )
                }
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<List<ReservationEntity>> getReservationsByDate(@RequestParam(value = "start_date") LocalDate startDate, @RequestParam(value = "end_date") LocalDate endDate) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getReservationsByDate(startDate, endDate));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/at_location")
    @Operation(
        summary = "Find all reservations by location ID",
        description = "Find all reservations by location ID",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ReservationEntity.class))
                    )
                }
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<List<ReservationEntity>> getReservationsByLocation(@RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getReservationsByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/by_category")
    @Operation(
        summary = "Find all reservations by category ID",
        description = "Find all reservations by category ID",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ReservationEntity.class))
                    )
                }
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<List<ReservationEntity>> getReservationsByCategory(@RequestParam(value = "category_id") Long categoryId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getReservationsByCategory(categoryId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // @GetMapping("/with_status")
    // @Operation(
    //     summary = "Find all reservations by status",
    //     description = "Find all reservations by status",
    //     tags = {"Reservation"},
    //     responses = {
    //         @ApiResponse(
    //             description = "Success",
    //             responseCode = "200",
    //             content = {
    //                 @Content(
    //                     mediaType = "application/json",
    //                     array = @ArraySchema(schema = @Schema(implementation = ReservationEntity.class))
    //                 )
    //             }
    //         ),
    //         @ApiResponse(
    //             description = "Internal server error",
    //             responseCode = "500",
    //             content = @Content
    //         )
    //     }
    // )
    // public ResponseEntity<List<ReservationEntity>> getReservationsByStatus(@RequestParam(value = "status") String status) throws Exception {
    //     try {
    //         return ResponseEntity.ok().body(service.getReservationsByStatus(status));
    //     } catch (Exception e) {
    //         throw new Exception(e.getMessage());
    //     }
    // }

    @GetMapping("/on_date_and_location")
    @Operation(
        summary = "Find all reservations between two dates by location ID",
        description = "Find all reservations between two dates by location ID",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ReservationEntity.class))
                    )
                }
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<List<ReservationEntity>> getReservationsByDateAndLocation(@RequestParam(value = "start_date") LocalDate startDate, @RequestParam(value = "end_date") LocalDate endDate, @RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getReservationsByDateAndLocation(startDate, endDate, locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PatchMapping("/cancel/{reservationId}")
    @Operation(
        summary = "Cancel a reservation",
        description = "Cancel a reservation by its ID",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationResponseDto.class))
            ),
            @ApiResponse(
                description = "Bad request",
                responseCode = "400",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<ReservationResponseDto> cancelReservation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long reservationId) throws ResourceNotFoundException {
        ReservationEntity reservation = service.cancelReservation(reservationId).orElseThrow(() -> new ResourceNotFoundException(String.format("Reservation ID %s not found!", reservationId)));
        return ResponseEntity.ok().body(ReservationResponseDto.fromReservationEntities(List.of(reservation)));
    }

    @PatchMapping("/book_car")
    @Operation(
        summary = "Assign a car to a booking",
        description = "Assign a car to a booking",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                description = "Bad request",
                responseCode = "400",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<String> bookCar(Long reservationId, CarEntity car) {
        Boolean isReserved = service.bookCar(reservationId, car);
        
        if (isReserved) {
            return ResponseEntity.ok().body(String.format("Car ID %s assigned to booking ID %s successfully!", car.getId(), reservationId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Reservation ID %s or car ID %s not found!", reservationId, car.getId()));
        }
    }

    @PostMapping("/total_invoice_amount")
    @Operation(
        summary = "Returns the total amount of the invoice",
        description = "Returns the total amount of the invoice",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = BigDecimal.class))
            ),
            @ApiResponse(
                description = "Bad request",
                responseCode = "400",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<BigDecimal> calculateTotalInvoiceAmount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody ReservationRequestDto reservation) throws BadRequestException {
        try {
            return ResponseEntity.ok().body(service.calculateTotalInvoiceAmount(reservation));
        } catch (Exception e) {
            throw new BadRequestException("Reservation properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping("/history")
    @Operation(
        summary = "Provides the user's reservation history",
        description = "Provides reservation history by user ID",
        tags = {"Reservation"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationResponseDto.class))
            ),
            @ApiResponse(
                description = "Resource not found",
                responseCode = "404",
                content = @Content
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content
            )
        }
    )
    public ResponseEntity<ReservationResponseDto> getUserBookingHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam(value = "client_id") Long clientId) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(ReservationResponseDto.fromReservationEntities(service.getReservationsByClient(clientId)));
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }
}
