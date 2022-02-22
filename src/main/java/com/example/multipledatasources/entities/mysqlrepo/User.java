package com.example.multipledatasources.entities.mysqlrepo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "name", columnDefinition = "varchar(120)")
    private String name;

    @Column(name = "dni", columnDefinition = "varchar(12)")
    private String dni;
}