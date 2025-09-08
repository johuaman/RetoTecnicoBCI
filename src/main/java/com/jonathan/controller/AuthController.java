package com.jonathan.controller;

import com.jonathan.exception.ErrorResponse;
import com.jonathan.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Validate token JWT", description = "verify token and email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "validation successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(
            @RequestParam String token,
            @RequestParam String email) {

        try {
            Map<String, Object> response = new HashMap<>();
            boolean isValid = jwtUtil.validateToken(token, email);

            if(!isValid)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Error al validar email y/o token."));

            response.put("token", token);
            response.put("email", email);
            response.put("isValid", "true");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Error al validar email y/o token."));
        }
    }
}
