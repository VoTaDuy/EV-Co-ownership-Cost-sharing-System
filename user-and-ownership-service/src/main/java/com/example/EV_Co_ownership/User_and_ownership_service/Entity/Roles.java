package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(name = "role_name")
    private String role_name;

    @Column(name = "created_date")
    private String created_date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Users> usersList;

}
