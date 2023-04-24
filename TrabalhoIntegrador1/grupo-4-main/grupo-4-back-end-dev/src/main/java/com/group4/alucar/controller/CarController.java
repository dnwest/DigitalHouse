package com.group4.alucar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.alucar.dto.CarRequestDto;
import com.group4.alucar.exception.handler.BadRequestException;
import com.group4.alucar.exception.handler.ResourceNotFoundException;
import com.group4.alucar.model.CarEntity;
import com.group4.alucar.service.CarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/car")
@Tag(name = "Car", description = "Endpoints for managing car")
public class CarController {
    @Autowired
    CarService service;

    @PostMapping
    @Operation(
        summary = "Create a car",
        description = "Create a car passing a JSON representation",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<CarEntity> save(@RequestBody CarRequestDto car) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(car));
        } catch (Exception e) {
            throw new BadRequestException("Car properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping
    @Operation(
        summary = "Find all cars",
        description = "Find all cars",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<List<CarEntity>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find a car",
        description = "Find a car by its ID",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<CarEntity> getById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(service.getById(id).get());
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    @PutMapping
    @Operation(
        summary = "Update a car",
        description = "Update a car passing a JSON representation",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<CarEntity> update(@RequestBody CarRequestDto car) throws BadRequestException {
        try {
            this.getById(car.getId());
            return ResponseEntity.ok().body(service.update(car));
        } catch (Exception e){
            throw new BadRequestException("Car properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a car",
        description = "Delete a car by its ID",
        tags = {"Car"},
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

    @GetMapping("/all_in_location")
    @Operation(
        summary = "Find all cars by location ID",
        description = "Find all cars by location ID",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<List<CarEntity>> getAllCarsByLocation(@RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAllCarsByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/available_in_location")
    @Operation(
        summary = "Find available cars by location ID",
        description = "Find available cars by location ID",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<List<CarEntity>> getAvailableCarsByLocation(@RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAvailableCarsByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/all_in_category")
    @Operation(
        summary = "Find all cars by category ID",
        description = "Find all cars by category ID",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<List<CarEntity>> getAllCarsByCategory(@RequestParam(value = "category_id") Long categoryId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAllCarsByCategory(categoryId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/available_in_category")
    @Operation(
        summary = "Find available cars by category ID",
        description = "Find available cars by category ID",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<List<CarEntity>> getAvailableCarsByCategory(@RequestParam(value = "category_id") Long categoryId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAvailableCarsByCategory(categoryId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/all_in_category_and_location")
    @Operation(
        summary = "Find all cars by category ID and location ID",
        description = "Find all cars by category ID and location ID",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<List<CarEntity>> getAllCarsByCategoryAndLocation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam(value = "category_id") Long categoryId, @RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAllCarsByCategoryAndLocation(categoryId, locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/available_in_category_and_location")
    @Operation(
        summary = "Find available cars by category ID and location ID",
        description = "Find available cars by category ID and location ID",
        tags = {"Car"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CarEntity.class))
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
    public ResponseEntity<List<CarEntity>> getAvailableCarsByCategoryAndLocation(@RequestParam(value = "category_id") Long categoryId, @RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAvailableCarsByCategoryAndLocation(categoryId, locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
