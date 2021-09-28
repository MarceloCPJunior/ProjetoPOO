package br.iesb.coo.apidemo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserEntity {
    private long UserId;
    private String nickName;
    private String email;
    private String password;
    private List<String> myList;
    private Date creationDate;
    private Date updateDate;
    private String token;

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<String> getMyList() { return myList; }

    public void setMyList(List<String> myList) { this.myList = myList; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
}
