package com.challenge.backend.api.resource;

import com.challenge.backend.api.dto.OrderDto;
import com.challenge.backend.exception.RegraNegocioException;
import com.challenge.backend.model.entity.Order;
import com.challenge.backend.service.OrderItemService;
import com.challenge.backend.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class OrderResourceTest {

	@Mock
	private OrderService orderService;

	@Mock
	private OrderItemService orderItemService;

	@InjectMocks
	private OrderResource orderResource;

	@Test
	public void testSetOrder() {
		// Cenário
		OrderDto dto = new OrderDto();
		dto.setUserId(1L);

		OrderDto.ProductDTO product1 = new OrderDto.ProductDTO();
		product1.setId(1L);
		product1.setAmount(2L);

		OrderDto.ProductDTO product2 = new OrderDto.ProductDTO();
		product2.setId(2L);
		product2.setAmount(1L);

		dto.setProducts(Arrays.asList(product1, product2));

		UUID orderId = UUID.randomUUID();
		when(orderItemService.setOrderItens(anyLong(), anyList(), anyString())).thenReturn(100.0);
		when(orderService.saveOrder(anyLong(), anyList(), UUID.fromString(anyString()), anyDouble())).thenReturn(
				Order.builder()
						.id(orderId)
						.userId(dto.getUserId())
						.totalPrice(100.0)
						.build()
		);

		// Ação
		ResponseEntity responseEntity = orderResource.setOrder(dto);

		// Verificação
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isInstanceOf(Order.class);
		Order order = (Order) responseEntity.getBody();
		assertThat(order.getId()).isEqualTo(orderId);
		assertThat(order.getUserId()).isEqualTo(dto.getUserId());
		assertThat(order.getTotalPrice()).isEqualTo(100.0);

		when(orderItemService.setOrderItens(anyLong(), anyList(), anyString())).thenReturn(100.0);

	}


	@Test
	public void testAlteraStatus() {
		// Cenário
		OrderDto dto = new OrderDto();
		dto.setId(UUID.randomUUID());
		dto.setStatus("COMPLETED");

		doReturn(Order.builder().build()).when(orderService).updateOrderStatus(any(UUID.class), anyString());

		// Ação
		ResponseEntity responseEntity = orderResource.AlteraStatus(dto);

		// Verificação
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo("Status do Pedido Atualizado");

		verify(orderService, times(1)).updateOrderStatus(dto.getId(), dto.getStatus());
	}
}
