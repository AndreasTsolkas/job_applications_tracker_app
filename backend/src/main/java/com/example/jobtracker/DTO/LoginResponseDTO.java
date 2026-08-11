package com.example.jobtracker.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private String token;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String userRole;
}
