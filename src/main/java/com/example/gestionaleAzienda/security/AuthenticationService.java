package com.example.gestionaleAzienda.security;


import com.example.gestionaleAzienda.domain.dto.request.create.AuthRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.RegisterRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.ChangePasswordRequest;
import com.example.gestionaleAzienda.domain.dto.response.AuthenticationResponse;
import com.example.gestionaleAzienda.domain.dto.response.ErrorResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.enums.Role;
import com.example.gestionaleAzienda.services.ComuneService;
import com.example.gestionaleAzienda.services.DipendenteService;
import com.example.gestionaleAzienda.services.PosizioneLavorativaService;
import com.example.gestionaleAzienda.services.TokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenBlackListService tokenBlackListService;
    @Autowired
    private JavaMailSender javaMailSender;

    public AuthenticationResponse register(RegisterRequest request) {
        Dipendente dipendente = Dipendente
                .builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .telefono(request.telefono())
                .dataNascita(request.dataNascita())
                .luogoNascita(comuneService.getById(request.comune_id().id()))
                .posizioneLavorativa(posizioneLavorativaService.getById(request.posizione_lavorativa_id().id()))
                .role(Role.TOCONFIRM)
                .build();

        String jwtToken = jwtService.generateToken(dipendente);
        dipendente.setRegistrationToken(jwtToken);
        dipendenteService.insertDipendente(dipendente);
        // TODO invio email di conferma
        String confirmationUrl = "http://localhost:8080/app/v1/auth/confirm?token=" + dipendente.getRegistrationToken();
        javaMailSender.send(createConfirmationEmail(dipendente.getEmail(), confirmationUrl));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email().toLowerCase(),
                request.password()
        ));
        Dipendente dipendente = dipendenteService.getByEmail(request.email());
        String token = jwtService.generateToken(dipendente);
        dipendente.setLastLogin(LocalDateTime.now());
        dipendenteService.insertDipendente(dipendente);
        return AuthenticationResponse.builder().token(token).build();
    }

    public GenericResponse logout(Long idDipendente, String token) {
        tokenBlackListService.insertToken(idDipendente,token);
        return GenericResponse.builder().message("Logout effettuato con successo").build();
    }

    private SimpleMailMessage createConfirmationEmail(String email, String confirmationUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email); // a chi mando la mail
        message.setReplyTo(""); // a chi rispondo se faccio "rispondi"
        message.setFrom(""); // da chi viene la mail
        message.setSubject("CONFERMA REGISTRAZIONE DIPENDENTE"); // il TITOLO!
        message.setText("Ciao! Clicca su questo link per confermare la registrazione! " + confirmationUrl); // il testo!
        return message; // ritorno il messaggio
    }

    public GenericResponse confirmRegistration(String token) {
        Dipendente dipendente = dipendenteService.getByRegistrationToken(token);
        dipendente.setRole(Role.UTENTE);
        dipendenteService.insertDipendente(dipendente);
        return GenericResponse
                .builder()
                .message("Account verificato con successo!")
                .build();
    }

    public Object changePassword(Long id_dipendente, ChangePasswordRequest request) {
        Dipendente dipendente = dipendenteService.getById(id_dipendente);
        if (!passwordEncoder.matches(request.oldPassword(), dipendente.getPassword())) {
            // se la vecchia password passata non coincide
            return ErrorResponse
                    .builder()
                    .exception("WrongPasswordException")
                    .message("La vecchia password non è corretta")
                    .build();
        }
        dipendente.setPassword(passwordEncoder.encode(request.newPassword())); // setto la nuova password
        dipendenteService.insertDipendente(dipendente);
        return GenericResponse
                .builder()
                .message("Password cambiata con successo")
                .build();
    }
}
