package br.iesb.cco.ouranimelistdemo3.model;

public class Review extends BaseDate {

    private Long id;
    private String tokenUser;
    private String tokenAnime;
    private String content;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    public String getTokenAnime() {
        return tokenAnime;
    }

    public void setTokenAnime(String tokenAnime) {
        this.tokenAnime = tokenAnime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
