package br.iesb.cco.ouranimelistdemo3.repository;

import br.iesb.cco.ouranimelistdemo3.dto.AnimeDTO;
import br.iesb.cco.ouranimelistdemo3.model.Anime;
import br.iesb.cco.ouranimelistdemo3.service.AnimeService;
import br.iesb.cco.ouranimelistdemo3.service.ReviewService;
import br.iesb.cco.ouranimelistdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class AnimeRepository implements CreationTokenInterface{
    List<Anime> animeList = new ArrayList<>();
    @Autowired
    AnimeService animeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;

    Long countId = 0L;

    public AnimeDTO findById(Long idanime){

        AnimeDTO animeDTO;
        for (Anime anime: animeList) {

            if(anime.getId().equals(idanime)){
                animeDTO = animeService.conversorEntityDTO(anime);
                return animeDTO;
            }
        }
        return null;
    }

    public String creatToken(){
        return UUID.randomUUID().toString();
    }

    public void saveAnime(AnimeDTO animeDTO){
        Anime anime = animeService.conversorDTOEntity(animeDTO);
        anime.setId(countId++);
        anime.setCreationDate(new Date());
        anime.setUpdateDate(new Date());
        anime.setToken(creatToken());
        animeList.add(anime);
    }

    public int deletAnimeId(Long idanime){
        for (Anime anime: animeList) {
            if(anime.getId().equals(idanime)){
                animeList.remove(anime);
                return 0;
            }
        }
        return 1;
    }

    public int updateAnimeId(AnimeDTO animeDTO){
        Anime anime = animeService.conversorDTOEntity(animeDTO);
        for (Anime anime1 : animeList) {
            if(anime1.getId().equals(anime.getId())){
                anime.setCreationDate(anime1.getCreationDate());
                anime.setUpdateDate(new Date());
                anime.setToken(anime1.getToken());
                animeList.remove(anime1);
                animeList.add(anime);
                return 0;
            }
        }
        return 1;
    }

    public List<Anime> getAnimeList(){
        return animeList;
    }

    public void deleteToken(String token){
        for (Anime anime: animeList) {
            for (String tokenAux: anime.getTokenReview()) {
                if(tokenAux.equals(token)){
                    anime.getTokenReview().remove(token);
                    return;
                }
            }
        }
    }
}
