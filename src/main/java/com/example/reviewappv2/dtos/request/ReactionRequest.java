package com.example.reviewappv2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionRequest {
    private UUID id;
    private UUID reviewId;
    private long userId;
}
