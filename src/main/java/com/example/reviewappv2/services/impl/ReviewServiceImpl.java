package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.ReactionRequest;
import com.example.reviewappv2.dtos.request.ReviewRequest;
import com.example.reviewappv2.dtos.response.ReactionResponse;
import com.example.reviewappv2.dtos.response.ReviewResponse;
import com.example.reviewappv2.exceptions.ReviewNotFoundException;
import com.example.reviewappv2.exceptions.UserNotFoundException;
import com.example.reviewappv2.models.Reaction;
import com.example.reviewappv2.models.Review;
import com.example.reviewappv2.repositories.ReactionRepository;
import com.example.reviewappv2.repositories.ReviewRepository;
import com.example.reviewappv2.repositories.UserRepository;
import com.example.reviewappv2.services.ReviewService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReviewResponse save(ReviewRequest reviewRequest) throws Exception {
        Review review = modelMapper.map(reviewRequest, Review.class);
        review.setDate(LocalDate.now());
        review.setReported(false);
        review.setUser(userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new Exception("No user found")));
        return modelMapper.map(reviewRepository.save(review), ReviewResponse.class);
    }

    @Override
    public ReviewResponse update(ReviewRequest reviewRequest) throws ReviewNotFoundException {
        Review review = modelMapper.map(reviewRequest, Review.class);

        Optional<Review> existingReview = reviewRepository.findById(reviewRequest.getId());
        if (existingReview.isPresent()) {
            return modelMapper.map(reviewRepository.save(review), ReviewResponse.class);
        } else {
            throw new ReviewNotFoundException("No review was found");
        }
    }

    @Override
    public ReactionResponse like(ReactionRequest reactionRequest) throws ReviewNotFoundException, UserNotFoundException {
        Reaction reaction = modelMapper.map(reactionRequest, Reaction.class);

        Optional<Reaction> existingReaction = reactionRepository.findByUserIdAndReviewId(reactionRequest.getUserId(), reactionRequest.getReviewId());

        if (existingReaction.isPresent()) {
            return modelMapper.map(existingReaction.get(), ReactionResponse.class);
        } else {
            reaction.setReview(reviewRepository.findById(reactionRequest.getReviewId()).orElseThrow(() -> new ReviewNotFoundException("Review not found")));
            reaction.setUser(userRepository.findById(reactionRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found")));
            return modelMapper.map(reactionRepository.save(reaction), ReactionResponse.class);
        }
    }

    @Override
    public void delete(UUID id) throws ReviewNotFoundException {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(id);
        } else {
            throw new ReviewNotFoundException("No review was found");
        }
    }

    @Override
    public List<ReviewResponse> findAll() {
        return reviewRepository.findAll().stream()
                .map(review -> modelMapper.map(review, ReviewResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> findAllReportedReviews() {
        return reviewRepository.findAllReportedReviews().stream()
                .map(review -> modelMapper.map(review, ReviewResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse findById(UUID id) throws ReviewNotFoundException {
        if(reviewRepository.findById(id).isPresent()) {
            return modelMapper.map(reviewRepository.findById(id).get(), ReviewResponse.class);
        }
        throw new ReviewNotFoundException("No review was found");
    }

    @Override
    public ReviewResponse report(UUID id) throws ReviewNotFoundException {
        if(reviewRepository.findById(id).isPresent()) {
            Review review = reviewRepository.findById(id).get();
            review.setReported(true);
            return modelMapper.map(reviewRepository.save(review), ReviewResponse.class);
        }
        throw new ReviewNotFoundException("No review was found");
    }
}
