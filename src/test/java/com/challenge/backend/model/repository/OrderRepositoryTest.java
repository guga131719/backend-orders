package com.challenge.backend.model.repository;

import com.challenge.backend.model.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testSaveOrder() {

		UUID orderId = UUID.randomUUID();
		Order order = Order.builder()
				.id(orderId)
				.userId(1)
				.uuid_string(orderId.toString())
				.status("PENDING")
				.totalPrice(100.0)
				.build();


		Order savedOrder = orderRepository.save(order);


		Assertions.assertThat(savedOrder.getId()).isEqualTo(orderId);
		Assertions.assertThat(savedOrder.getUserId()).isEqualTo(1);
		Assertions.assertThat(savedOrder.getUuid_string()).isEqualTo(orderId.toString());
		Assertions.assertThat(savedOrder.getStatus()).isEqualTo("PENDING");
		Assertions.assertThat(savedOrder.getTotalPrice()).isEqualTo(100.0);
	}


	@Test
	public void testUpdateStatusByUuidString() {

		UUID orderId = UUID.randomUUID();
		Order order = Order.builder()
				.id(orderId)
				.userId(1)
				.uuid_string(orderId.toString())
				.status("PENDING")
				.totalPrice(100.0)
				.build();

		orderRepository.save(order);


		int updatedRows = orderRepository.updateStatusByUuidString("COMPLETED", orderId.toString());


		Assertions.assertThat(updatedRows).isEqualTo(1);

		Order updatedOrder = orderRepository.findByUuidString(orderId.toString());
		Assertions.assertThat(updatedOrder).isNotNull();
		Assertions.assertThat(updatedOrder.getStatus()).isEqualTo("COMPLETED");
	}
}