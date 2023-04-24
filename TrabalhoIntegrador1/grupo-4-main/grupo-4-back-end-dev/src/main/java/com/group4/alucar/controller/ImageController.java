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
import com.group4.alucar.model.ImageEntity;
import com.group4.alucar.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/image")
@Tag(name = "Image", description = "Endpoints for managing image")
public class ImageController {
    @Autowired
    ImageService service;

    @PostMapping
    @Operation(
        summary = "Create a image",
        description = "Create a image passing its JSON representation",
        tags = {"Image"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageEntity.class))
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
    public ResponseEntity<ImageEntity> save(@RequestBody ImageEntity image) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(image));
        } catch (Exception e) {
            throw new BadRequestException("Location properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping
    @Operation(
        summary = "Find all images",
        description = "Find all images",
        tags = {"Image"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ImageEntity.class))
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
    public ResponseEntity<List<ImageEntity>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find a image",
        description = "Find a image by its ID",
        tags = {"Image"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageEntity.class))
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
    public ResponseEntity<ImageEntity> getById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(service.getById(id).get());
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    @PutMapping
    @Operation(
        summary = "Update a image",
        description = "Update a image passing its JSON representation",
        tags = {"Image"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageEntity.class))
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
    public ResponseEntity<ImageEntity> update(@RequestBody ImageEntity image) throws BadRequestException {
        try {
            this.getById(image.getId());
            return ResponseEntity.ok().body(image);
        } catch (Exception e){
            throw new BadRequestException("Location properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a image",
        description = "Delete a image by its ID",
        tags = {"Image"},
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
