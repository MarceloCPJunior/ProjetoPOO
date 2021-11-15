package br.iesb.cco.ouranimelistdemo3.service;

import br.iesb.cco.ouranimelistdemo3.dto.UserDTO;
import br.iesb.cco.ouranimelistdemo3.model.User;
import br.iesb.cco.ouranimelistdemo3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public int validationLogin(User user){

        for (User userAux : userRepository.getUsers()) {
            if(user.getEmail().equals(userAux.getEmail())){
                if(user.getPassword().equals(userAux.getPassword())){
                    UserDTO userDTO = userService.conversorEntityDTO(userAux);
                    userRepository.setUserLogged(userDTO);
                    return 0;
                }
                else{
                    return 1;
                }
            }
        }
        return 2;
    }

    public int validationRegister(User user){
        if(!emailValidation(user.getEmail())){
            return 0;
        }
        else if(nickNameValidation(user.getNickName())){
            return 1;
        }
        else if(!passwordValidadtion(user.getPassword())){
            return 2;
        }
        else{
            return 3;
        }
    }

    public static boolean emailValidation(String email){

        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        boolean isEmailValid = matcher.matches();

        return isEmailValid;
    }

    public boolean nickNameValidation(String nickName){

        if(nickName != null && nickName.length() > 3 && nickName.length() < 10){

            Pattern regex = Pattern.compile("[$&+,:;=?@#|'<>.^*()%!-]");
            Matcher matcher = regex.matcher(nickName);

            if(matcher.find()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return true;
        }
    }

    public boolean passwordValidadtion(String password){
        if (password.length() < 6 && password.length() > 15) return false;

        boolean containNumber = false;
        boolean containsUpperCase = false;
        boolean foundLowercase = false;
        boolean foundSymbol = false;
        for (char c : password.toCharArray()) {
            if(c == ' '){
                return false;
            }
            if (c >= '0' && c <= '9') {
                containNumber = true;
            } else if (c >= 'A' && c <= 'Z') {
                containsUpperCase = true;
            } else if (c >= 'a' && c <= 'z') {
                foundLowercase = true;
            } else {
                foundSymbol = true;
            }
        }
        return containNumber && containsUpperCase && foundLowercase && foundSymbol;
    }
}
