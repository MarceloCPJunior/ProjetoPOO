package br.iesb.cco.ouranimelistdemo3.controller;

import br.iesb.cco.ouranimelistdemo3.dto.AnimeDTO;
import br.iesb.cco.ouranimelistdemo3.model.Anime;
import br.iesb.cco.ouranimelistdemo3.repository.AnimeRepository;
import br.iesb.cco.ouranimelistdemo3.repository.ReviewRepository;
import br.iesb.cco.ouranimelistdemo3.repository.UserRepository;
import br.iesb.cco.ouranimelistdemo3.service.AnimeService;
import br.iesb.cco.ouranimelistdemo3.service.AuthService;
import br.iesb.cco.ouranimelistdemo3.service.ReviewService;
import br.iesb.cco.ouranimelistdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OurAnimeList/anime/review")
public class ReviewController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewService reviewService;
    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    AuthService authService;
    @Autowired
    AnimeService animeService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserService userService;

    @PostMapping("/add-review")
    @ResponseBody
    public ResponseEntity<String> addReview(@RequestParam(value = "idanime") Long idanime, @RequestParam(value = "review") String review){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        AnimeDTO animeDTO = animeRepository.findById(idanime);
        if(animeDTO == null){
            return new ResponseEntity<>("Anime não Encontrado!", HttpStatus.NOT_FOUND);
        }

        Anime anime = animeService.conversorDTOEntity(animeDTO);

        reviewRepository.addReview(anime, review);

        return new ResponseEntity<>("Review incluído com sucesso!",HttpStatus.OK);
    }

    @PostMapping("/edit-review")
    public ResponseEntity<String> updateReview(@RequestParam(value = "idreview") Long idreview, @RequestParam(value = "edit") String edit){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int result = reviewRepository.editReview(idreview, edit);

        if(result == 0){
            return new ResponseEntity<>("Review atualizado com sucesso!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review não encontrada",HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete-review")
    public ResponseEntity<String> deleteReview(@RequestParam(value = "idreview") Long idreview){
        if (!userService.userIsLogged()){
            return new ResponseEntity<>("Faça o login!",HttpStatus.BAD_REQUEST);
        }

        int result = reviewRepository.deleteReview(idreview);
        if(result == 0){
            return new ResponseEntity<>("Review deletado com sucesso!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review não encontrada",HttpStatus.NOT_FOUND);
    }
}
