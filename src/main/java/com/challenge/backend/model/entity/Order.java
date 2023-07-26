package com.challenge.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "order" , schema = "ecommerce_vivo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "status")
	private String status;

	@Column(name = "uuid_string")
	private String uuid_string;

	@Column(name = "total_price")
	private double totalPrice;

}
