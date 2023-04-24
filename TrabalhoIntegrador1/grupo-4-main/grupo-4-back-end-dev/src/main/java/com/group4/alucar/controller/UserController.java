// package com.group4.alucar.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.group4.alucar.dto.UserDto;
// import com.group4.alucar.exception.handler.BadRequestException;
// import com.group4.alucar.exception.handler.ResourceNotFoundException;
// import com.group4.alucar.model.UserEntity;
// import com.group4.alucar.service.UserService;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.ArraySchema;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @Controller
// @RequestMapping("/user")
// @Tag(name = "User", description = "Endpoints for managing user")
// public class UserController {
//     @Autowired
//     UserService service;

//     @PostMapping
//     @Operation(
//         summary = "Create a user",
//         description = "Create a user passing its JSON representation",
//         tags = {"User"},
//         responses = {
//             @ApiResponse(
//                 description = "Created",
//                 responseCode = "201",
//                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
//             ),
//             @ApiResponse(
//                 description = "Bad request",
//                 responseCode = "400",
//                 content = @Content
//             ),
//             @ApiResponse(
//                 description = "Internal server error",
//                 responseCode = "500",
//                 content = @Content
//             )
//         }
//     )
//     public ResponseEntity<UserDto> save(@RequestBody UserEntity user) throws BadRequestException {
//         try {
//             return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
//         } catch (Exception e) {
//             throw new BadRequestException("User properties are invalid. " + e.getLocalizedMessage());
//         }
//     }

//     @GetMapping
//     @Operation(
//         summary = "Find all users",
//         description = "Find all users",
//         tags = {"User"},
//         responses = {
//             @ApiResponse(
//                 description = "Success",
//                 responseCode = "200",
//                 content = {
//                     @Content(
//                         mediaType = "application/json",
//                         array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
//                     )
//                 }
//             ),
//             @ApiResponse(
//                 description = "Internal server error",
//                 responseCode = "500",
//                 content = @Content
//             )
//         }
//     )
//     public ResponseEntity<List<UserDto>> getAll() throws Exception {
//         try {
//             return ResponseEntity.ok().body(service.getAll());
//         } catch (Exception e) {
//             throw new Exception(e.getMessage());
//         }
//     }

//     @GetMapping("/{id}")
//     @Operation(
//         summary = "Find a user",
//         description = "Find a user by its ID",
//         tags = {"User"},
//         responses = {
//             @ApiResponse(
//                 description = "Success",
//                 responseCode = "200",
//                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
//             ),
//             @ApiResponse(
//                 description = "Resource not found",
//                 responseCode = "404",
//                 content = @Content
//             ),
//             @ApiResponse(
//                 description = "Internal server error",
//                 responseCode = "500",
//                 content = @Content
//             )
//         }
//     )
//     public ResponseEntity<UserDto> getById(@PathVariable Long id) throws ResourceNotFoundException {
//         try {
//             return ResponseEntity.ok().body(service.getById(id));
//         } catch (Exception e){
//             throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
//         }
//     }

//     @PutMapping
//     @Operation(
//         summary = "Update a user",
//         description = "Update a user passing its JSON representation",
//         tags = {"User"},
//         responses = {
//             @ApiResponse(
//                 description = "Updated",
//                 responseCode = "200",
//                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
//             ),
//             @ApiResponse(
//                 description = "Bad request",
//                 responseCode = "400",
//                 content = @Content
//             ),
//             @ApiResponse(
//                 description = "Internal server error",
//                 responseCode = "500",
//                 content = @Content
//             )
//         }
//     )
//     public ResponseEntity<UserDto> update(@RequestBody UserEntity user) throws BadRequestException {
//         try {
//             this.save(user);
//             return ResponseEntity.ok().body(service.save(user));
//         } catch (Exception e){
//             throw new BadRequestException("user properties are invalid. " + e.getLocalizedMessage());
//         }
//     }

//     @DeleteMapping("/{id}")
//     @Operation(
//         summary = "Delete a user",
//         description = "Delete a user by its ID",
//         tags = {"User"},
//         responses = {
//             @ApiResponse(
//                 description = "No Content",
//                 responseCode = "204",
//                 content = @Content
//             ),
//             @ApiResponse(
//                 description = "Resource not found",
//                 responseCode = "404",
//                 content = @Content
//             ),
//             @ApiResponse(
//                 description = "Internal server error",
//                 responseCode = "500",
//                 content = @Content
//             )
//         }
//     )
//     public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
//         try {
//             service.delete(id);
//             return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//         } catch (Exception e){
//             throw new ResourceNotFoundException("ID does not exist. " + e.getLocalizedMessage());
//         }
//     }
// }
