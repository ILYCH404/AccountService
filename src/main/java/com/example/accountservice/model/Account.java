package com.example.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor()
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //Маркер, который говорит, использовался ли данный счет
    @Column(name = "active", nullable = false, columnDefinition = "bool default false")
    private Boolean active = false;

    @Column(name = "amount")
    private Long amount;


}
