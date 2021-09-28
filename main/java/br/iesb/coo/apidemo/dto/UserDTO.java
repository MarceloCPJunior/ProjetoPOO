package br.iesb.coo.apidemo.dto;

import java.util.List;

public class UserDTO {
    private String nickName;
    private String email;
    private String password;
    private List<String> myList;

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<String> getMyList() { return myList; }

    public void setMyList(List<String> myList) { this.myList = myList; }
}