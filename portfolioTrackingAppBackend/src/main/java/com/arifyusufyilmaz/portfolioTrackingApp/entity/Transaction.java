package com.arifyusufyilmaz.portfolioTrackingApp.entity;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction {
    //TODO manytoone portfolio
    @Id
    @GeneratedValue(generator = "Transaction", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Transaction", sequenceName = "TRANSACTION_ID_SEQ")
    private Long id;




}
