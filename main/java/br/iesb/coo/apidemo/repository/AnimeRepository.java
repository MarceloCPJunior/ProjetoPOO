package br.iesb.coo.apidemo.repository;

import br.iesb.coo.apidemo.model.AnimeEntity;
import br.iesb.coo.apidemo.service.AnimeService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimeRepository {
    List<AnimeEntity> animesEntity = new ArrayList<>();

    public List<AnimeEntity> getAnime(){
        return animesEntity;
    }

    public void save(AnimeEntity anime){
        animesEntity.add(anime);
    }

    public void update(AnimeEntity anime, int i){
        animesEntity.get(i).setAnimeId(anime.getAnimeId());
        animesEntity.get(i).setName(anime.getName());
        animesEntity.get(i).setGenres(anime.getGenres());
        animesEntity.get(i).setEpisodes(anime.getEpisodes());
        animesEntity.get(i).setStats(anime.isStats());
        animesEntity.get(i).setAuthor(anime.getAuthor());
        animesEntity.get(i).setStudio(anime.getStudio());
        animesEntity.get(i).setSeason(anime.getSeason());
        animesEntity.get(i).setClassification(anime.getClassification());
        animesEntity.get(i).setStartDay(anime.getStartDay());
        animesEntity.get(i).setStartMonth(anime.getStartMonth());
        animesEntity.get(i).setStartYear(anime.getStartYear());
    }

    public void delete(int i){
        animesEntity.remove(animesEntity.get(i));
    }
}
