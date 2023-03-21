package com.grupo4.api_alucar.controller;

import com.grupo4.api_alucar.exception.handler.BadRequestException;
import com.grupo4.api_alucar.exception.handler.ResourceNotFoundException;
import com.grupo4.api_alucar.model.ClientEntity;
import com.grupo4.api_alucar.service.ClientService;

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
@RequestMapping("/client")
@Tag(name = "Client", description = "Endpoints for managing client")
public class ClientController {
    @Autowired
    ClientService service;

    @PostMapping
    @Operation(
        summary = "Create a client",
        description = "Create a client passing its JSON representation",
        tags = {"Client"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientEntity.class))
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
    public ResponseEntity<ClientEntity> save(@RequestBody ClientEntity client) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(client));
        } catch (Exception e) {
            throw new BadRequestException("Client properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @GetMapping
    @Operation(
        summary = "Find all categories",
        description = "Find all categories",
        tags = {"Client"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ClientEntity.class))
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
    public ResponseEntity<List<ClientEntity>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find a client",
        description = "Find a client by its id",
        tags = {"Client"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientEntity.class))
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
    public ResponseEntity<ClientEntity> getById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok().body(service.getById(id).get());
        } catch (Exception e){
            throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
        }
    }

    @PutMapping
    @Operation(
        summary = "Update a client",
        description = "Update a client passing its JSON representation",
        tags = {"Client"},
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientEntity.class))
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
    public ResponseEntity<ClientEntity> update(@RequestBody ClientEntity client) throws BadRequestException {
        try {
            this.getById(client.getId());
            return ResponseEntity.ok().body(service.update(client));
        } catch (Exception e){
            throw new BadRequestException("Client properties are invalid. " + e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a client",
        description = "Delete a client by its id",
        tags = {"Client"},
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
