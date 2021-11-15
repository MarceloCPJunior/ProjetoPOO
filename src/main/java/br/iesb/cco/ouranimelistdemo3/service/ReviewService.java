package br.iesb.cco.ouranimelistdemo3.service;

import br.iesb.cco.ouranimelistdemo3.dto.ReviewDTO;
import br.iesb.cco.ouranimelistdemo3.model.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public ReviewDTO conversorEntityDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setId(review.getId());
        reviewDTO.setContent(review.getContent());

        return reviewDTO;
    }

    public Review conversorDTOEntity(ReviewDTO reviewDTO){
        Review review = new Review();

        review.setId(reviewDTO.getId());
        review.setContent(reviewDTO.getContent());

        return review;
    }
}
