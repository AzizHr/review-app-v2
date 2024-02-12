package com.example.reviewappv2.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {

    private UUID id;
    private String title;
    private String message;
    private UserResponse user;

}
