package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.ReqAuthDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.ResAuthDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.AuthServive;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServive authServive;

    @PostMapping(path = "/login")
    public ResponseEntity<ResAuthDto> login(@Valid @RequestBody ReqAuthDto authDto) {
        String jwt = this.authServive.logIn(authDto);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", jwt);

        ResAuthDto resAuthDto = ResAuthDto.builder().message("Login Exitosos").build();

        return ResponseEntity.ok().headers(responseHeaders).body(resAuthDto);
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<ResAuthDto> signUp(@Valid @RequestBody ReqAuthDto authDto) {
        this.authServive.signUp(authDto);
        ResAuthDto resAuthDto = ResAuthDto.builder().message("Usuario creado con exito.").build();

        return ResponseEntity.ok().body(resAuthDto);
    }
}
