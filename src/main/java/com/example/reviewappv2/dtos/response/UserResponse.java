package com.example.reviewappv2.dtos.response;

import com.example.reviewappv2.inums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long id;
    private String firstname;
    private String lastname;
    private Role role;

}
