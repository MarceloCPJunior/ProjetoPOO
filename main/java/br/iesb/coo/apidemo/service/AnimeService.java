package br.iesb.coo.apidemo.service;

import br.iesb.coo.apidemo.dto.AnimeDTO;
import br.iesb.coo.apidemo.model.AnimeEntity;
import br.iesb.coo.apidemo.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository repository;

    public List<AnimeDTO> showAnimes(){
        List<AnimeDTO> animesListDTO = new ArrayList<>();
        List<AnimeEntity> animesListEntity = repository.getAnime();
        
        for(int i = 0; i < animesListEntity.size(); i++){
            animesListDTO.add(conversorEntityDTO(animesListEntity.get(i)));
        }

        return animesListDTO;
    }

    public void save(AnimeDTO animeDTO){
        AnimeEntity animeEntity = conversorDTOEntity(animeDTO);
        repository.save(animeEntity);
    }

    public AnimeDTO search(long id){
        AnimeDTO animeDTO = new AnimeDTO();
        List<AnimeEntity> animesEntity = repository.getAnime();

        for (int i = 0; i < animesEntity.size(); i++) {
            if(animesEntity.get(i).getAnimeId() == id){

                animeDTO = conversorEntityDTO(animesEntity.get(i));

                return animeDTO;
            }
        }
        return animeDTO;
    }

    public void update(AnimeDTO animeDTO){
        AnimeEntity animeEntity = conversorDTOEntity(animeDTO);
        List<AnimeEntity> listAnimesEntity = repository.getAnime();

        for (int i = 0; i < listAnimesEntity.size(); i++) {
            if(animeEntity.getAnimeId() == listAnimesEntity.get(i).getAnimeId()){
                repository.update(animeEntity, i);
            }
        }
    }

    public void delete(long id){
        List<AnimeEntity> animesList = repository.getAnime();

        for (int i = 0; i < animesList.size(); i++) {
            if(animesList.get(i).getAnimeId() == id){
                repository.delete(i);
            }
        }
    }

    public AnimeDTO conversorEntityDTO(AnimeEntity anime){
        AnimeDTO animeDTO = new AnimeDTO();

        animeDTO.setAnimeId(anime.getAnimeId());
        animeDTO.setName(anime.getName());
        animeDTO.setGenres(anime.getGenres());
        animeDTO.setEpisodes(anime.getEpisodes());
        animeDTO.setStats(anime.isStats());
        animeDTO.setAuthor(anime.getAuthor());
        animeDTO.setStudio(anime.getStudio());
        animeDTO.setSeason(anime.getSeason());
        animeDTO.setClassification(anime.getClassification());
        animeDTO.setStartDay(anime.getStartDay());
        animeDTO.setStartMonth(anime.getStartMonth());
        animeDTO.setStartYear(anime.getStartYear());

        return animeDTO;
    }

    public AnimeEntity conversorDTOEntity(AnimeDTO anime){
        AnimeEntity animeEntity = new AnimeEntity();

        animeEntity.setAnimeId(anime.getAnimeId());
        animeEntity.setName(anime.getName());
        animeEntity.setGenres(anime.getGenres());
        animeEntity.setEpisodes(anime.getEpisodes());
        animeEntity.setStats(anime.isStats());
        animeEntity.setAuthor(anime.getAuthor());
        animeEntity.setStudio(anime.getStudio());
        animeEntity.setSeason(anime.getSeason());
        animeEntity.setClassification(anime.getClassification());
        animeEntity.setStartDay(anime.getStartDay());
        animeEntity.setStartMonth(anime.getStartMonth());
        animeEntity.setStartYear(anime.getStartYear());

        return animeEntity;
    }
}
