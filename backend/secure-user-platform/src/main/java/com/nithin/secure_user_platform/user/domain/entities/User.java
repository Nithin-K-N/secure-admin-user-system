package com.nithin.secure_user_platform.user.domain.entities;

import com.nithin.secure_user_platform.utility.enums.Roles;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "users")
@Getter @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    // Identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, length = 50)
    private String username;

    @Setter
    @Column(nullable = false)
    private String firstName;

    @Setter
    @Column
    private String lastName;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    // Security
    @Setter
    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private UserStates state;

    // Auditing
    private LocalDateTime lastLoginAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Lifecycle callbacks
    @PrePersist
    void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    // domain behavior
    public void ban() {
        this.state = UserStates.BANNED;
    }

    public void activate() {
        this.state = UserStates.ACTIVE;
    }

    public void promote(){ this.role = Roles.ADMIN; }
    public void demote(){ this.role = Roles.USER; }

}