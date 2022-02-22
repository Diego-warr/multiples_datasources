package com.example.multipledatasources.entities.posgrerepo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "name", columnDefinition = "varchar(120)")
    private String name;

    @Column(name = "code", columnDefinition = "varchar(32)")
    private String code;
}
