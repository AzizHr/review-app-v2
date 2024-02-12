package com.example.reviewappv2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
