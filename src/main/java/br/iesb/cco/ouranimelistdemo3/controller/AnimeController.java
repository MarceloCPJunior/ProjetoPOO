package br.iesb.cco.ouranimelistdemo3.controller;

import br.iesb.cco.ouranimelistdemo3.dto.AnimeDTO;
import br.iesb.cco.ouranimelistdemo3.model.Anime;
import br.iesb.cco.ouranimelistdemo3.repository.AnimeRepository;
import br.iesb.cco.ouranimelistdemo3.service.AnimeService;
import br.iesb.cco.ouranimelistdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/OurAnimeList/anime")
public class AnimeController {

    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    AnimeService animeService;
    @Autowired
    UserService userService;

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<String> salvarAnime(@RequestBody AnimeDTO animeDTO){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int authorize = userService.userFunction();

        if(authorize != 1 && authorize != 2){
            return new ResponseEntity<>("Função não permitida!", HttpStatus.PRECONDITION_REQUIRED);
        }

        if(animeDTO == null){
            return new ResponseEntity<>("Não há anime para salvar", HttpStatus.PRECONDITION_REQUIRED);
        }

        animeRepository.saveAnime(animeDTO);
        return new ResponseEntity<>("Salvo com sucesso!",HttpStatus.CREATED);
    }

    @GetMapping("/search-id")
    @ResponseBody
    public ResponseEntity<AnimeDTO> buscaranimeid(@RequestParam(value = "idanime") Long idanime){
        AnimeDTO anime = animeRepository.findById(idanime);
        if(anime == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(anime, HttpStatus.FOUND);
        }
    }

    @GetMapping("/search-all")
    @ResponseBody
    public ResponseEntity<List<AnimeDTO>> searchllanimes(){
        List<AnimeDTO> animeDTOList = new ArrayList<>();
        List<Anime> animeList = animeRepository.getAnimeList();
        for (Anime anime: animeList) {
            animeDTOList.add(animeService.conversorEntityDTO(anime));
        }
        return new ResponseEntity<>(animeDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/delete-id")
    @ResponseBody
    public ResponseEntity<String> deletAnimeid(@RequestParam(value = "idanime") Long idanime){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }
        int authorize = userService.userFunction();

        if(authorize != 1 && authorize != 2){
            return new ResponseEntity<>("Função não permitida!", HttpStatus.PRECONDITION_REQUIRED);
        }

        int result = animeRepository.deletAnimeId(idanime);
        if(result == 0){
            return new ResponseEntity<>("Anime deletado com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Anime não encontrado", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-id")
    @ResponseBody
    public ResponseEntity<String> updateAnimeId(@RequestBody AnimeDTO animeDTO){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int authorize = userService.userFunction();

        if(authorize != 1 && authorize != 2){
            return new ResponseEntity<>("Função não permitida!", HttpStatus.PRECONDITION_REQUIRED);
        }

        int result = animeRepository.updateAnimeId(animeDTO);
        if(result == 0){
            return new ResponseEntity<>("Anime atualizado com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Anime não encontrado!", HttpStatus.BAD_REQUEST);
    }
}