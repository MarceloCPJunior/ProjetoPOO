package br.iesb.coo.apidemo.controller;
import br.iesb.coo.apidemo.dto.AnimeDTO;
import br.iesb.coo.apidemo.repository.AnimeRepository;
import br.iesb.coo.apidemo.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animes")
public class AnimeController {

    @Autowired
    private AnimeService service;

    @GetMapping
    public List<AnimeDTO> showAnimes() {
        List<AnimeDTO> dtos = service.showAnimes();
        return dtos;
    }

    @PostMapping("/new")
    public void newAnime(@RequestBody AnimeDTO animeDTO){
        service.save(animeDTO);
    }

    @GetMapping("/search")
    public AnimeDTO buscaId(@RequestBody AnimeDTO id){

        AnimeDTO animeDTO = service.search(id.getAnimeId());

        return animeDTO;
    }

    @PutMapping("/update")
    public void update(@RequestBody AnimeDTO animeDTO){
        service.update(animeDTO);
    }

    @DeleteMapping("/delete")
    public void deleteId(@RequestBody AnimeDTO id){
        service.delete(id.getAnimeId());
    }
}
