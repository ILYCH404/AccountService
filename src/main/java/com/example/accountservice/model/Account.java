package com.example.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor()
public class Account {
    @Id
    private Integer id;

    @Column(name = "amount")
    private Long amount;


}
