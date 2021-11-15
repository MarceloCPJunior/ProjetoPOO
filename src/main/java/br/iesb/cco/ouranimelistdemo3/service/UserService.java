package br.iesb.cco.ouranimelistdemo3.service;

import br.iesb.cco.ouranimelistdemo3.dto.UserDTO;
import br.iesb.cco.ouranimelistdemo3.model.User;
import br.iesb.cco.ouranimelistdemo3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    public boolean verifyLogged(UserDTO user){
        if (user == null){
            return false;
        }
        return true;
    }

    public int userFunction(){
        String function = userRepository.getUserLogged().getFunction();

        switch(function){
            case "User":
                return 0;
            case "Uploader":
                return 1;
            case "Admin":
                return 2;
        }
        return 3;
    }

    public boolean userIsLogged(){
        if(userRepository.getUserLogged() != null){
            return true;
        }
        return false;
    }

    public User conversorDTOEntity(UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setNickName(userDTO.getNickName());
        user.setEmail(userDTO.getEmail());
        user.setMyList(userDTO.getMyList());
        user.setFunction(userDTO.getFunction());
        user.setTokenReview(userDTO.getTokenReview());

        return user;
    }

    public UserDTO conversorEntityDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setNickName(user.getNickName());
        userDTO.setEmail(user.getEmail());
        userDTO.setMyList(user.getMyList());
        userDTO.setFunction(user.getFunction());
        userDTO.setTokenReview(user.getTokenReview());

        return userDTO;
    }
}
