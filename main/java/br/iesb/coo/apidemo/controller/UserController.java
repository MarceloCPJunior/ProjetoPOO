package br.iesb.coo.apidemo.controller;

import br.iesb.coo.apidemo.dto.UserDTO;
import br.iesb.coo.apidemo.model.UserEntity;
import br.iesb.coo.apidemo.repository.UserRepository;
import br.iesb.coo.apidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService service;

    @GetMapping
    public List<UserDTO> getUser() {
        List<UserEntity> users = userRepository.getUsers();
        List<UserDTO> userDTO = service.convert(users);



        return userDTO;
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UserDTO userDTO){
        service.updateUser(userDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody UserEntity user){
        service.delete(user);
    }

    @PostMapping("/mylist/add")
    public ResponseEntity<String> addAnime(@RequestBody String anime){
        int answer = service.saveMyAnime(anime);

        if(answer == 0){
            return ResponseEntity.badRequest().body("User not logged");
        }
        else if(answer == 1){
            return ResponseEntity.badRequest().body("Anime already inserted");
        }
        else if(answer == 2){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().body("Anime not found");
        }
    }

    @DeleteMapping("mylist/remove")
    public void removeAnime(@RequestBody String anime){
        service.removeMyAnime(anime);
    }
}
