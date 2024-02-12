package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.ReactionRequest;
import com.example.reviewappv2.dtos.request.ReviewRequest;
import com.example.reviewappv2.dtos.response.ReactionResponse;
import com.example.reviewappv2.dtos.response.ReviewResponse;
import com.example.reviewappv2.exceptions.ReviewNotFoundException;
import com.example.reviewappv2.exceptions.UserNotFoundException;
import com.example.reviewappv2.services.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@EnableMethodSecurity
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReactionResponse> like(@RequestBody ReactionRequest reactionRequest) throws UserNotFoundException, ReviewNotFoundException {
        return new ResponseEntity<>(reviewService.like(reactionRequest), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ReviewResponse> add(@Valid @RequestBody ReviewRequest reviewRequest) throws Exception {
        return new ResponseEntity<>(reviewService.save(reviewRequest), HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'MODERATOR')")
    public ResponseEntity<ReviewResponse>  edit(@Valid @RequestBody ReviewRequest reviewRequest) throws Exception {
        return new ResponseEntity<>(reviewService.update(reviewRequest), HttpStatus.OK);
    }

    @PostMapping("/{id}/report")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ResponseEntity<ReviewResponse> report(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(reviewService.report(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws ReviewNotFoundException {
    reviewService.delete(id);
        return new ResponseEntity<>("Review delete with success", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ReviewResponse>> reviews() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReviewResponse> review(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(reviewService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/reported")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<ReviewResponse>> reportedReviews() {
        return new ResponseEntity<>(reviewService.findAllReportedReviews(), HttpStatus.OK);
    }

}
