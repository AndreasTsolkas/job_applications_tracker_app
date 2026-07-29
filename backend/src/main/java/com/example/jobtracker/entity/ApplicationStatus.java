package com.example.jobtracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "application_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
