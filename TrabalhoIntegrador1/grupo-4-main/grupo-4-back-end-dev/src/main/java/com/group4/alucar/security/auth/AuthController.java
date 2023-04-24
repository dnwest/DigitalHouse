package com.group4.alucar.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group4.alucar.security.jwt.JwtService;
import com.group4.alucar.security.jwt.TokenRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/auth")

public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthController(JwtService jwtService, AuthenticationManager authManager) {
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @PostMapping
    @Operation(
        summary = "Create a token",
        description = "Create a token passing a valid user",
        tags = {"Authentication"},
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(mediaType = "text/plain; charset=utf-8", schema = @Schema(implementation = String.class))
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
    public ResponseEntity<?> authenticate(@RequestBody TokenRequest user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.username(),user.password()));
        String token = jwtService.buildToken(authentication);
        log.info("jwt generated: Bearer " + token);
        return ResponseEntity.ok().body("Bearer " + token);
    }
}
