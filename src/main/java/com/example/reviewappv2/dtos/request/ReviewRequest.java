package com.example.reviewappv2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    private UUID id;
    private String title;
    private String message;
    private long userId;

}
