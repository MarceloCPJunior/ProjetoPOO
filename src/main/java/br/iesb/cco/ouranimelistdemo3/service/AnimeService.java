package br.iesb.cco.ouranimelistdemo3.service;

import br.iesb.cco.ouranimelistdemo3.dto.AnimeDTO;
import br.iesb.cco.ouranimelistdemo3.model.Anime;
import br.iesb.cco.ouranimelistdemo3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService{

    @Autowired
    UserRepository userRepository;

    public AnimeDTO conversorEntityDTO(Anime anime){
        AnimeDTO animeDTO = new AnimeDTO();

        animeDTO.setId(anime.getId());
        animeDTO.setName(anime.getName());
        animeDTO.setSynopsis(anime.getSynopsis());
        animeDTO.setGenres(anime.getGenres());
        animeDTO.setEpisodes(anime.getEpisodes());
        animeDTO.setStatus(anime.isStatus());
        animeDTO.setStudios(anime.getStudios());
        animeDTO.setSource(anime.getSource());
        animeDTO.setSeason(anime.getSeason());
        animeDTO.setAired(anime.getAired());
        animeDTO.setTokenReview(anime.getTokenReview());

        return animeDTO;
    }

    public Anime conversorDTOEntity(AnimeDTO animeDTO){
        Anime anime = new Anime();

        anime.setId(animeDTO.getId());
        anime.setName(animeDTO.getName());
        anime.setSynopsis(animeDTO.getSynopsis());
        anime.setGenres(animeDTO.getGenres());
        anime.setEpisodes(animeDTO.getEpisodes());
        anime.setStatus(animeDTO.isStatus());
        anime.setStudios(animeDTO.getStudios());
        anime.setSource(animeDTO.getSource());
        anime.setSeason(animeDTO.getSeason());
        anime.setAired(animeDTO.getAired());
        anime.setTokenReview(animeDTO.getTokenReview());

        return anime;
    }
}
