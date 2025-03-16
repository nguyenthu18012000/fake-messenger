package com.main.fakeMessenger.pojo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Size(max = 20)
    @Column(name = "phone", unique = true, length = 20)
    private String phone;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Size(max = 255)
    @Column(name = "image_url", unique = true)
    private String image_url;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    @Column(name = "deleted_at", nullable = false)
    private Date deleted_at;
}
