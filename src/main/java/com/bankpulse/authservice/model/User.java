// User.java
package com.bankpulse.authservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    private String id; // UUID

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private String name;
    private String address;
    private String sex;
    private String nominee;
    private String occupation;

    @Column(nullable = false)
    private String password;

    private boolean enabled;  // for email verification/activation
}
