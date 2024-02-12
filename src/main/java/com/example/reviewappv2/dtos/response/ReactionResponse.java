package com.example.reviewappv2.dtos.response;

import com.example.reviewappv2.dtos.request.ReviewRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionResponse {

    private UUID id;
    private ReviewRequest review;
    private UserResponse user;

}
