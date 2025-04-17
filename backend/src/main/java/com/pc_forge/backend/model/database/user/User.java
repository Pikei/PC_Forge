package com.pc_forge.backend.model.database.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_data", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "user_data_user_id_key", columnNames = {"user_id"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @JsonIgnore
    private Integer id;

    @Column(name = "username", nullable = false, length = 200)
    private String username;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "first_name", nullable = false, length = Integer.MAX_VALUE)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = Integer.MAX_VALUE)
    private String lastName;

    @Column(name = "phone_number", length = Integer.MAX_VALUE)
    private String phoneNumber;

}