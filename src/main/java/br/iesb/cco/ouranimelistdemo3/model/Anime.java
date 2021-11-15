package br.iesb.cco.ouranimelistdemo3.model;

import java.util.List;

public class Anime extends BaseDate {

    private Long id;
    private String name;
    private String synopsis;
    private List<String> genres;
    private int episodes;
    private boolean status;
    private List<String> studios;
    private String source;
    private String season;
    private String aired;
    private List<String> tokenReview;
    private String token;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSynopsis() { return synopsis; }

    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public List<String> getGenres() { return genres; }

    public void setGenres(List<String> genres) { this.genres = genres; }

    public int getEpisodes() { return episodes; }

    public void setEpisodes(int episodes) { this.episodes = episodes; }

    public boolean isStatus() { return status; }

    public void setStatus(boolean status) { this.status = status; }

    public List<String> getStudios() { return studios; }

    public void setStudios(List<String> studios) { this.studios = studios; }

    public String getSource() { return source; }

    public void setSource(String source) { this.source = source; }

    public String getSeason() { return season; }

    public void setSeason(String season) { this.season = season; }

    public String getAired() { return aired; }

    public void setAired(String aired) { this.aired = aired; }

    public List<String> getTokenReview() {
        return tokenReview;
    }

    public void setTokenReview(List<String> tokenReview) {
        this.tokenReview = tokenReview;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
