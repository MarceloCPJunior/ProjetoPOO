package br.iesb.cco.ouranimelistdemo3.model;

import java.util.List;

public class User extends BaseDate {
    private Long id;
    private String nickName;
    private String email;
    private String password;
    private List<List<String>> myList;
    private String function;
    private List<String> tokenReview;
    private String token;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFunction() { return function; }

    public void setFunction(String function) { this.function = function; }

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<List<String>> getMyList() { return myList; }

    public void setMyList(List<List<String>> myList) { this.myList = myList; }

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
