package com.grupo4.api_alucar.controller;

import com.grupo4.api_alucar.exception.handler.BadRequestException;
import com.grupo4.api_alucar.exception.handler.ResourceNotFoundException;
import com.grupo4.api_alucar.model.ProductEntity;
import com.grupo4.api_alucar.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
@Tag(name = "Product", description = "Endpoints for managing product")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping
    @Operation(
        summary = "Create a product",
        description = "Create a product passing its JSON representation",
        tags = {"Product"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductEntity.class))
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
    public ResponseEntity<ProductEntity> save(@RequestBody ProductEntity product) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
        } catch (Exception e) {
            throw new BadRequestException("Product properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping
    @Operation(
        summary = "Find all categories",
        description = "Find all categories",
        tags = {"Product"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class))
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
    public ResponseEntity<List<ProductEntity>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find a product",
        description = "Find a product by its id",
        tags = {"Product"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductEntity.class))
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
    public ResponseEntity<ProductEntity> getById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(service.getById(id).get());
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    @PutMapping
    @Operation(
        summary = "Update a product",
        description = "Update a product passing its JSON representation",
        tags = {"Product"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductEntity.class))
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
    public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity product) throws BadRequestException {
        try {
            this.getById(product.getId());
            return ResponseEntity.ok().body(service.update(product));
        } catch (Exception e){
            throw new BadRequestException("Product properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a product",
        description = "Delete a product by its id",
        tags = {"Product"},
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

    @GetMapping("/location_id={locationId}")
    @Operation(
        summary = "Find all cars in one city",
        description = "Find all cars in a city by location id",
        tags = {"Product"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class))
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
    public ResponseEntity<List<ProductEntity>> findProductsByLocation(@PathVariable Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.findProductsByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/category_id={categoryId}")
    @Operation(
        summary = "Find all cars in a category",
        description = "Find all cars in a category by category id",
        tags = {"Product"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class))
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
    public ResponseEntity<List<ProductEntity>> findProductsByCategory(@PathVariable Long categoryId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.findProductsByCategory(categoryId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
