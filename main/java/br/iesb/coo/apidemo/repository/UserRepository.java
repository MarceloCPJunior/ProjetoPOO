package br.iesb.coo.apidemo.repository;

import br.iesb.coo.apidemo.service.UserService;
import br.iesb.coo.apidemo.dto.UserDTO;
import br.iesb.coo.apidemo.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<UserEntity> usersEntity = new ArrayList<>();
    List<UserDTO> usersDTO = new ArrayList<>();

    private UserService service;

    public List<UserEntity> getUsers(){
        return usersEntity;
    }

    public void saveUser(UserEntity user){
        usersEntity.add(user);
    }

    public void saveAnime(String anime){
        for (int i = 0; i < usersEntity.size(); i++) {
            if(usersEntity.get(i).getToken().equals(service.getToken())){
                usersEntity.get(i).getMyList().add(anime);
            }
        }
    }

    public void removeAnime(int i, int j){
        usersEntity.remove(usersEntity.get(i).getMyList().get(j));
    }

    public void update(UserEntity user, int i){
        usersEntity.get(i).setNickName(user.getNickName());
        usersEntity.get(i).setEmail(user.getEmail());
        usersEntity.get(i).setPassword(user.getPassword());
        usersEntity.get(i).setMyList(user.getMyList());
    }

    public void delete(int i){
        usersEntity.remove(usersEntity.get(i));
    }
}
