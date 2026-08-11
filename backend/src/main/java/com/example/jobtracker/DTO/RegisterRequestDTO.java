package com.example.jobtracker.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
