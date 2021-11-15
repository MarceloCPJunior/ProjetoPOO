package br.iesb.cco.ouranimelistdemo3.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String nickName;
    private String email;
    private List<List<String>> myList;
    private List<String> tokenReview;
    private String function;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<List<String>> getMyList() {
        return myList;
    }

    public void setMyList(List<List<String>> myList) {
        this.myList = myList;
    }

    public List<String> getTokenReview() {
        return tokenReview;
    }

    public void setTokenReview(List<String> tokenReview) {
        this.tokenReview = tokenReview;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
