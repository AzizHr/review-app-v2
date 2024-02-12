package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.ReactionRequest;
import com.example.reviewappv2.dtos.request.ReviewRequest;
import com.example.reviewappv2.dtos.response.ReactionResponse;
import com.example.reviewappv2.dtos.response.ReviewResponse;
import com.example.reviewappv2.exceptions.ReviewNotFoundException;
import com.example.reviewappv2.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewResponse save(ReviewRequest reviewRequest) throws Exception;
    ReviewResponse update(ReviewRequest review) throws Exception;
    void delete(UUID id) throws ReviewNotFoundException;
    List<ReviewResponse> findAll();
    List<ReviewResponse> findAllReportedReviews();
    ReactionResponse like(ReactionRequest reactionRequest) throws ReviewNotFoundException, UserNotFoundException;
    ReviewResponse findById(UUID id) throws Exception;
    ReviewResponse report(UUID id) throws ReviewNotFoundException;

}
