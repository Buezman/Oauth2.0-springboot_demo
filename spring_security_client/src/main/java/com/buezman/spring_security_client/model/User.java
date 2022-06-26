package com.buezman.spring_security_client.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;
}
