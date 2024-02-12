package com.example.reviewappv2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate date;
    private String title;
    private String message;
    private boolean isReported;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Reaction> reactions;

}
