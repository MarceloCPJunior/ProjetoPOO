package br.iesb.cco.ouranimelistdemo3.controller;

import br.iesb.cco.ouranimelistdemo3.dto.AnimeDTO;
import br.iesb.cco.ouranimelistdemo3.dto.UserDTO;
import br.iesb.cco.ouranimelistdemo3.model.User;
import br.iesb.cco.ouranimelistdemo3.repository.AnimeRepository;
import br.iesb.cco.ouranimelistdemo3.repository.UserRepository;
import br.iesb.cco.ouranimelistdemo3.service.AuthService;
import br.iesb.cco.ouranimelistdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/OurAnimeList/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    AuthService authService;

    @PostMapping("/create-users")
    @ResponseBody
    public void creatAdmin(){
        userRepository.creatUsers();
    }

    @GetMapping("/search-all")
    @ResponseBody
    public ResponseEntity<List<UserDTO>> searchAllUsers(){
        List<User> userList = userRepository.getUsers();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user: userList) {
            userDTOList.add(userService.conversorEntityDTO(user));
        }

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/search-id")
    @ResponseBody
    public ResponseEntity<UserDTO> buscaruserid(@RequestParam(value = "iduser") Long iduser){
        UserDTO user = userRepository.findById(iduser);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
    }

    @DeleteMapping("/delete-id")
    @ResponseBody
    public ResponseEntity<String> deletAnimeid(@RequestParam(value = "iduser") Long iduser){

        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int authorize = userService.userFunction();

        if(authorize != 2){
            return new ResponseEntity<>("Função não permitida!", HttpStatus.PRECONDITION_REQUIRED);
        }

        int result = userRepository.deletUserId(iduser);
        if(result == 0){
            return new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Usuário não encontrado!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-id")
    @ResponseBody
    public ResponseEntity<String> updateAnimeId(@RequestBody User newUser){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int authorize = userService.userFunction();

        if(authorize != 2){
            return new ResponseEntity<>("Função não permitida!", HttpStatus.PRECONDITION_REQUIRED);
        }

        int result = authService.validationRegister(newUser);

        if(result == 0){
            return new ResponseEntity<>("Email inválido!", HttpStatus.BAD_REQUEST);
        }
        else if(result == 1){
            return new ResponseEntity<>("NickName inválido!", HttpStatus.BAD_REQUEST);
        }
        else if(result == 2){
            return new ResponseEntity<>("Senha inválida!", HttpStatus.BAD_REQUEST);
        }

        userRepository.updateAnime(newUser);

        return new ResponseEntity<>("Atualizado com sucesso!", HttpStatus.OK);
    }

    @PostMapping("/mylist-add-id")
    @ResponseBody
    public ResponseEntity<String> saveInUserList(@RequestParam(value = "idanime") Long idanime){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        AnimeDTO anime = animeRepository.findById(idanime);
        if(anime == null){
            return new ResponseEntity<>("Anime não Encontrado!", HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = userRepository.getUserLogged();

        userRepository.addInUserList(anime, userDTO.getId());
        return new ResponseEntity<>("Anime incluído na lista com sucesso!",HttpStatus.OK);
    }

    @DeleteMapping("/mylist-delete-id")
    @ResponseBody
    public ResponseEntity<String> deleteInUserList(@RequestParam(value = "idanime") Long idanime){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int result = userRepository.deleteInUserList(idanime);
        if (result == 0){
            return new ResponseEntity<>("Anime removido de mylist com sucesso!",HttpStatus.OK);
        }
        else if(result == 1){
            return new ResponseEntity<>("Anime não encontrado em mylist!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Falha com sucesso!",HttpStatus.EXPECTATION_FAILED);
    }

    @PutMapping("/update-authorization/{id}")
    @ResponseBody
    public ResponseEntity<String> getEmployeesById(@PathVariable Long id, @RequestParam String newAuthorization) {
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int authorize = userService.userFunction();

        if(authorize != 2){
            return new ResponseEntity<>("Função não permitida!", HttpStatus.PRECONDITION_REQUIRED);
        }

        int result = userRepository.updateAuth(id, newAuthorization);

        if(result == 0){
            return new ResponseEntity<>("Autorização alterada com sucesso", HttpStatus.OK);
        }
        return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }
}
