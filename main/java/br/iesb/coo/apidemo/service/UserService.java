package br.iesb.coo.apidemo.service;

import br.iesb.coo.apidemo.dto.UserDTO;
import br.iesb.coo.apidemo.model.AnimeEntity;
import br.iesb.coo.apidemo.model.UserEntity;
import br.iesb.coo.apidemo.repository.AnimeRepository;
import br.iesb.coo.apidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope
public class UserService {

    private List<UserEntity> users = new ArrayList<>();
    private String tokenLastLogin = null;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimeRepository animeRepository;

    public String login(UserEntity user) {
        String email = user.getEmail();
        String password = user.getPassword();

        List<UserEntity> userList = new ArrayList<>();

        userList = userRepository.getUsers();

        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).equals(email) && userList.get(i).equals(password)){
                tokenLastLogin = userList.get(i).getToken();
                return userList.get(i).getToken();
            }
        }

        return null;
    }

    public String getToken(){
        return tokenLastLogin;
    }

    public int signup(UserDTO user) {
        if(user.getNickName().trim().equals("") || user.getNickName().trim().split(" ").length < 2) {
            return 1;
        }
        if (!user.getEmail().contains("@")) {
            return 2;
        }
        if (user.getPassword().length() < 6) {
            return 3;
        }

        saveUser(user);

        return 0;
    }

    public void saveUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();

        userEntity.setNickName(userDTO.getNickName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());

        String token = UUID.randomUUID().toString();
        userEntity.setToken(token);

        userRepository.saveUser(userEntity);
    }

    public int saveMyAnime(String anime){
        List<AnimeEntity> animesList = animeRepository.getAnime();
        List<UserEntity> usersList = userRepository.getUsers();

        if(tokenLastLogin == null){
            return 0;
        }

        for (int i = 0; i < usersList.size(); i++) {
            for (int j = 0; j < usersList.get(i).getMyList().size(); j++) {
                if(usersList.get(i).getMyList().get(j).equals(anime)){
                    return 1;
                }
            }
        }

        for (int i = 0; i < animesList.size(); i++) {
            if(animesList.get(i).getName().trim().toLowerCase().equals(anime.trim())){
                userRepository.saveAnime(anime);
                return 2;
            }
        }

        return 3;
    }

    public void removeMyAnime(String anime){
        List<UserEntity> users = userRepository.getUsers();
        String tok = getToken();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getToken().equals(tok)){
                for (int j = 0; j < users.get(i).getMyList().size(); j++) {
                    if(users.get(i).getMyList().get(j).equals(anime)){
                        userRepository.removeAnime(i, j);
                    }
                }
            }
        }
    }

    public void updateUser(UserDTO user){
        UserEntity userEntity = convertDTOEntity(user);
        List<UserEntity> usersList = userRepository.getUsers();

        for (int i = 0; i < userRepository.getUsers().size(); i++) {
            if(getToken().equals(usersList.get(i).getToken())){
                userRepository.update(userEntity, i);
            }
        }
    }

    public void delete(UserEntity userEntity){
        List<UserEntity> users = userRepository.getUsers();

        String email = userEntity.getEmail();
        String password = userEntity.getPassword();

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getEmail().equals(email) && (users.get(i).getPassword().equals(password))){
                userRepository.delete(i);
            }
        }
    }

    UserDTO usersDTO = new UserDTO();
    List<UserDTO> listDTO = new ArrayList<>();

    public List<UserDTO> convert(List<UserEntity> usersEntity){

        for(int i = 0; i < usersEntity.size(); i++){
            usersDTO.setNickName(usersEntity.get(i).getNickName());
            usersDTO.setEmail(usersEntity.get(i).getEmail());
            usersDTO.setPassword(usersEntity.get(i).getPassword());

            listDTO.add(usersDTO);
        }

        return listDTO;
    }

    public UserDTO convert(UserEntity userEntity){
        usersDTO.setNickName(userEntity.getNickName());
        usersDTO.setEmail(userEntity.getEmail());
        usersDTO.setPassword(userEntity.getPassword());

        return usersDTO;
    }

    public UserEntity convertDTOEntity(UserDTO user){
        UserEntity userEntity = new UserEntity();

        userEntity.setNickName(user.getNickName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        return userEntity;
    }
}
