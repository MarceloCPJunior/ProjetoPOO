package br.iesb.cco.ouranimelistdemo3.repository;

import br.iesb.cco.ouranimelistdemo3.dto.UserDTO;
import br.iesb.cco.ouranimelistdemo3.model.Anime;
import br.iesb.cco.ouranimelistdemo3.model.Review;
import br.iesb.cco.ouranimelistdemo3.model.User;
import br.iesb.cco.ouranimelistdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class ReviewRepository implements CreationTokenInterface {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    UserService userService;

    List<Review> reviewList = new ArrayList<>();
    Long countId = 0L;

    public String creatToken(){
        return UUID.randomUUID().toString();
    }

    public void addReview(Anime anime, String review){
        UserDTO userDTO = userRepository.getUserLogged();
        User user = userService.conversorDTOEntity(userDTO);

        Review newReview = new Review();

        String token = creatToken();

        for (Anime animeAux: animeRepository.getAnimeList()) {
            if(animeAux.getId().equals(anime.getId())){
                if(animeAux.getTokenReview() == null){
                    List<String> list = new ArrayList<>();
                    animeAux.setTokenReview(list);
                }
                animeAux.getTokenReview().add(token);
                newReview.setTokenAnime(animeAux.getToken());
                break;
            }
        }

        for (User userAux: userRepository.getUsers()) {
            if(userAux.getId().equals(user.getId())){
                if(userAux.getTokenReview() == null){
                    List<String> list = new ArrayList<>();
                    userAux.setTokenReview(list);
                }
                userAux.getTokenReview().add(token);
                newReview.setTokenUser(userAux.getToken());
                break;
            }
        }

        newReview.setId(countId++);
        newReview.setToken(token);
        newReview.setContent(review);
        newReview.setCreationDate(new Date());
        newReview.setUpdateDate(new Date());

        reviewList.add(newReview);
    }

    public int editReview(Long idReview, String edit){
        for (Review review : reviewList) {
            if(review.getId().equals(idReview)){
                String txt = review.getContent();
                txt += "\nedit: "+edit;
                review.setContent(txt);
                return 0;
            }
        }
        return 1;
    }

    public int deleteReview(Long idreview){
        for (Review review : reviewList) {
            if(review.getId().equals(idreview)){
                animeRepository.deleteToken(review.getToken());
                userRepository.deleteToken(review.getToken());
                reviewList.remove(review);
                return 0;
            }
        }
        return 1;
    }
}
