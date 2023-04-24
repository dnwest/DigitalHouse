package com.group4.alucar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.alucar.dto.CategoryDto;
import com.group4.alucar.exception.handler.BadRequestException;
import com.group4.alucar.exception.handler.ResourceNotFoundException;
import com.group4.alucar.model.CategoryEntity;
import com.group4.alucar.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/category")
@Tag(name = "Category", description = "Endpoints for managing category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping
    @Operation(
        summary = "Create a category",
        description = "Create a category passing its JSON representation",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryEntity.class))
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
    public ResponseEntity<CategoryEntity> save(@RequestBody CategoryEntity category) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(category));
        } catch (Exception e) {
            throw new BadRequestException("Category properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping
    @Operation(
        summary = "Find all categories",
        description = "Find all categories",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CategoryEntity.class))
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
    public ResponseEntity<List<CategoryEntity>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find a category",
        description = "Find a category by its ID",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryEntity.class))
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
    public ResponseEntity<CategoryEntity> getById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(service.getById(id).get());
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    @PutMapping
    @Operation(
        summary = "Update a category",
        description = "Update a category passing its JSON representation",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryEntity.class))
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
    public ResponseEntity<CategoryEntity> update(@RequestBody CategoryEntity category) throws BadRequestException {
        try {
            this.getById(category.getId());
            return ResponseEntity.ok().body(service.update(category));
        } catch (Exception e){
            throw new BadRequestException("Category properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a category",
        description = "Delete a category by its ID",
        tags = {"Category"},
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
        summary = "Find all categories by location ID",
        description = "Find all categories by location ID",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CategoryEntity.class))
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
    public ResponseEntity<List<CategoryEntity>> getAllCategoriesByLocation(@RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAllCategoriesByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/available_in_location")
    @Operation(
        summary = "Find available categories by location ID",
        description = "Find available categories by location ID",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CategoryEntity.class))
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
    public ResponseEntity<List<CategoryEntity>> getAvailableCategoriesByLocation(@RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAvailableCategoriesByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("with_cars/all_in_location")
    @Operation(
        summary = "Find all categories and cars by location ID",
        description = "Find all categories and cars by location ID",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))
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
    public ResponseEntity<List<CategoryDto>> getAllCategoriesAndCarsByLocation(@RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAllCategoriesAndCarsByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("with_cars/available_in_location")
    @Operation(
        summary = "Find available categories and cars by location ID",
        description = "Find available categories and cars by location ID",
        tags = {"Category"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))
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
    public ResponseEntity<List<CategoryDto>> getAvailableCategoriesAndCarsByLocation(@RequestParam(value = "location_id") Long locationId) throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAvailableCategoriesAndCarsByLocation(locationId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
