package com.example.gestionaleAzienda.security;


import com.example.gestionaleAzienda.domain.dto.request.create.AuthRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.RegisterRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.ChangePasswordRequest;
import com.example.gestionaleAzienda.domain.dto.response.AuthenticationResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.CREATED);
    }

    @PostMapping("/logout/{id_dipendente}")
    public ResponseEntity<GenericResponse> logout(@PathVariable Long id_dipendente, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(authenticationService.logout(id_dipendente, token), HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<GenericResponse> confirmRegistration(@RequestParam String token) {
        return new ResponseEntity<>(authenticationService.confirmRegistration(token), HttpStatus.CREATED);
    }

    @PostMapping("/change_pw/{id_dipendente}")
    public ResponseEntity<?> changePassword(@PathVariable Long id_dipendente, @RequestBody ChangePasswordRequest request) {
        Object result = authenticationService.changePassword(id_dipendente, request);
        if (result.getClass() == GenericResponse.class) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }

}
