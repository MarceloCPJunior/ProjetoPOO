package br.iesb.cco.ouranimelistdemo3.repository;

import br.iesb.cco.ouranimelistdemo3.dto.AnimeDTO;
import br.iesb.cco.ouranimelistdemo3.dto.UserDTO;
import br.iesb.cco.ouranimelistdemo3.model.User;
import br.iesb.cco.ouranimelistdemo3.service.ReviewService;
import br.iesb.cco.ouranimelistdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository implements CreationTokenInterface{
    List<User> userList = new ArrayList<>();
    UserDTO userLogged = new UserDTO();
    Long countId = 0L;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    ReviewService reviewService;


    public int deleteInUserList(Long idanime){
        UserDTO userDTO = userRepository.getUserLogged();
        User user = userService.conversorDTOEntity(userDTO);

        for (User userAux: userRepository.getUsers()) {
            if(userAux.getId().equals(userDTO.getId())){
                for (List<String> list: userAux.getMyList()){
                    if(list.get(0).equals(idanime.toString())){
                        user.getMyList().remove(list);
                        return 0;
                    }
                }
                return 1;
            }
        }
        return 2;
    }

    public void addInUserList(AnimeDTO animeDTO, long iduser) {
        List<List<String>> list = new ArrayList<>();
        List<String> animeInfo = new ArrayList<>();
        String id = animeDTO.getId().toString();

        animeInfo.add(id);
        animeInfo.add(animeDTO.getName());

        for (User user : userList) {
            if(user.getId() == iduser){
                if(user.getMyList() == null){
                    user.setMyList(list);
                }
                user.getMyList().add(animeInfo);
                return;
            }
        }
    }

    public UserDTO findById(Long iduser){
        UserDTO userDTO;
        for (User user : userList) {
            if(user.getId().equals(iduser)){
                userDTO = userService.conversorEntityDTO(user);
                return userDTO;
            }
        }
        return null;
    }

    public int deletUserId(Long iduser){
        for (User user : userList) {
            if(user.getId().equals(iduser)){
                userList.remove(user);
                return 0;
            }
        }
        return 1;
    }

    public void updateAnime(User newUser){
        for (User user : userList) {
            if(user.getId().equals(newUser.getId())){
                newUser.setCreationDate(user.getCreationDate());
                newUser.setUpdateDate(new Date());
                newUser.setToken(user.getToken());
                userList.remove(user);
                userList.add(newUser);
                return;
            }
        }
    }

    public UserDTO getUserLogged(){
        return userLogged;
    }

    public void setUserLogged(UserDTO userDTO){
        userLogged = userDTO;
    }

    public List<User> getUsers(){
        return userList;
    }

    public String creatToken(){
        return UUID.randomUUID().toString();
    }

    public void saveUser(User user) {
        user.setId(countId++);
        user.setCreationDate(new Date());
        user.setUpdateDate(new Date());
        user.setFunction("User");
        user.setToken(creatToken());

        userList.add(user);
    }

    public void deleteToken(String token){
        for (User user: userList) {
            if(user.getId().equals(userLogged.getId())){
                for (String tokenAux: user.getTokenReview()) {
                    if(tokenAux.equals(token)){
                        user.getTokenReview().remove(token);
                        return;
                    }
                }
            }
        }
    }

    public int updateAuth(Long id, String newAuthorization){
        for (User user : userList) {
            if(user.getId().equals(id)){
                user.setFunction(newAuthorization);
                return 0;
            }
        }
        return 1;
    }

    public void creatUsers(){
        User userAdmin = new User();
        userAdmin.setId(1111L);
        userAdmin.setEmail("admin1@admin.ad");
        userAdmin.setNickName("Admin1");
        userAdmin.setFunction("Admin");
        userAdmin.setPassword("Aaaaa_123");
        userAdmin.setCreationDate(new Date());
        userAdmin.setUpdateDate(new Date());
        userAdmin.setToken(creatToken());

        User userUser = new User();
        userUser.setId(2222L);
        userUser.setEmail("User1@user.us");
        userUser.setNickName("User1");
        userUser.setFunction("User");
        userUser.setPassword("Bbbbb_123");
        userUser.setCreationDate(new Date());
        userUser.setUpdateDate(new Date());
        userUser.setToken(creatToken());

        User userUploader = new User();
        userUploader.setId(3333L);
        userUploader.setEmail("Uploader1@uploader.up");
        userUploader.setNickName("Uploader1");
        userUploader.setFunction("Uploader");
        userUploader.setPassword("Ccccc_123");
        userUploader.setCreationDate(new Date());
        userUploader.setUpdateDate(new Date());
        userUploader.setToken(creatToken());

        userList.add(userAdmin);
        userList.add(userUser);
        userList.add(userUploader);
    }
}
