package com.recordslabel.labelapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recordslabel.labelapp.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "utilizator", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Email
    private String email;

    @JsonIgnore
    @ToString.Exclude
    private String password;

    @Column(name = "nume")
    private String firstName;

    @Column(name = "prenume")
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

}
