package br.iesb.cco.ouranimelistdemo3.controller;

import br.iesb.cco.ouranimelistdemo3.model.User;
import br.iesb.cco.ouranimelistdemo3.repository.UserRepository;
import br.iesb.cco.ouranimelistdemo3.service.AuthService;
import br.iesb.cco.ouranimelistdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OurAnimeList/user")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<String> signup(@RequestBody User user){
        int result = authService.validationRegister(user);

        if(result == 0){
            return new ResponseEntity<>("Email inválido!", HttpStatus.BAD_REQUEST);
        }
        else if(result == 1){
            return new ResponseEntity<>("NickName inválido!", HttpStatus.BAD_REQUEST);
        }
        else if(result == 2){
            return new ResponseEntity<>("Senha inválida!", HttpStatus.BAD_REQUEST);
        }

        userRepository.saveUser(user);
        return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody User user){
        int result = authService.validationLogin(user);

        if(result == 1){
            return new ResponseEntity<>("Senha incorreta!", HttpStatus.BAD_REQUEST);
        }
        else if(result == 2){
            return new ResponseEntity<>("Email incorreto!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Logado com sucesso!", HttpStatus.OK);
    }
}