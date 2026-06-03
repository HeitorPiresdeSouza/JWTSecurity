package br.eti.heitor.JWTSecurity.security.controller;

import br.eti.heitor.JWTSecurity.security.entities.AuthRequestDTO;
import br.eti.heitor.JWTSecurity.security.entities.AuthResponse;
import br.eti.heitor.JWTSecurity.security.entities.User;
import br.eti.heitor.JWTSecurity.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;


@RestController
public class AuthApi {
    
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    JwtTokenUtil jwtUtil;
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
            
            User user = (User) authentication.getPrincipal();
            String acessToken = jwtUtil.generateAcessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), acessToken);
            
            return ResponseEntity.ok().body(response);
            
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
