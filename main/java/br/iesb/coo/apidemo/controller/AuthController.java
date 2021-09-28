package br.iesb.coo.apidemo.controller;

import br.iesb.coo.apidemo.dto.UserDTO;
import br.iesb.coo.apidemo.model.UserEntity;
import br.iesb.coo.apidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        String token = service.login(user);
        if (token == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Authorization",token);
        return ResponseEntity.ok().headers(responseHeader).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDto) {
        int result = service.signup(userDto);

        if (result == 1) {
            return ResponseEntity.badRequest().body("Invalid Nick Name!");
        } else if (result == 2) {
            return ResponseEntity.badRequest().body("E-mail de usuario invalido!");
        } else if (result == 3) {
            return ResponseEntity.badRequest().body("Senha deve ter mais que 6 caracteres.");
        }



        return ResponseEntity.ok().build();
    }
}
