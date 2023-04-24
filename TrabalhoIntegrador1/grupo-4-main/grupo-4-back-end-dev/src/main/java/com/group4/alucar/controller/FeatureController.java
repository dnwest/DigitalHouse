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

import com.group4.alucar.exception.handler.BadRequestException;
import com.group4.alucar.exception.handler.ResourceNotFoundException;
import com.group4.alucar.model.FeatureEntity;
import com.group4.alucar.service.FeatureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/feature")
@Tag(name = "Feature", description = "Endpoints for managing feature")
public class FeatureController {
    @Autowired
    FeatureService service;

    @PostMapping
    @Operation(
        summary = "Create a feature",
        description = "Create a feature passing its JSON representation",
        tags = {"Feature"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeatureEntity.class))
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
    public ResponseEntity<FeatureEntity> save(@RequestBody FeatureEntity feature) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(feature));
        } catch (Exception e) {
            throw new BadRequestException("Location properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping
    @Operation(
        summary = "Find all features",
        description = "Find all features",
        tags = {"Feature"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = FeatureEntity.class))
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
    public ResponseEntity<List<FeatureEntity>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find a feature",
        description = "Find a feature by its ID",
        tags = {"Feature"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeatureEntity.class))
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
    public ResponseEntity<FeatureEntity> getById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(service.getById(id).get());
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    @PutMapping
    @Operation(
        summary = "Update a feature",
        description = "Update a feature passing its JSON representation",
        tags = {"Feature"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeatureEntity.class))
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
    public ResponseEntity<FeatureEntity> update(@RequestBody FeatureEntity feature) throws BadRequestException {
        try {
            this.getById(feature.getId());
            return ResponseEntity.ok().body(feature);
        } catch (Exception e){
            throw new BadRequestException("Location properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a feature",
        description = "Delete a feature by its ID",
        tags = {"Feature"},
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
}
