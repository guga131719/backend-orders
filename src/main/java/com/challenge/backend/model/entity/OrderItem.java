package com.challenge.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orderitem", schema = "ecommerce_vivo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {


    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "order_uuid_string")
    private String order_uuid_string;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "partial_amount")
    private double partialAmount;

    @Column(name = "id_product")
    private int idProduct;

}