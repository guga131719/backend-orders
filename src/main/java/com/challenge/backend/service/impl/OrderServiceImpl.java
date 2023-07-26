package com.challenge.backend.service.impl;


import com.challenge.backend.exception.UuidNaoEncontrado;
import com.challenge.backend.integration.ProductApi;
import com.challenge.backend.integration.UserApi;
import com.challenge.backend.integration.domain.Product;
import com.challenge.backend.integration.domain.User;
import com.challenge.backend.model.entity.*;
import com.challenge.backend.model.enums.StatusOrder;
import com.challenge.backend.model.repository.OrderItemRepository;
import com.challenge.backend.model.repository.OrderRepository;
import com.challenge.backend.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository repository;
	private OrderItemRepository orderItemrepository;

	double precoTotalCarrinho=0;
	public OrderServiceImpl(
			OrderRepository repository,OrderItemRepository orderItemrepository) {
		super();
		this.repository = repository;
		this.orderItemrepository = orderItemrepository;

	}


	@Override
	@Transactional
	public Order saveOrder(Long userId, List<Long> productIds,UUID UuidOrder,double totalPrice) {

		UserApi userApi = new UserApi();
		User returnUserApi=userApi.getUserById(userId);

		Order order = new Order();
		order.setId(UuidOrder);
		order.setStatus(String.valueOf(StatusOrder.PENDING));
		order.setUserId(returnUserApi.getId());
		order.setUuid_string(UuidOrder.toString());
		order.setTotalPrice(totalPrice);

		return repository.save(order);
	}


	public String updateOrderStatus(UUID orderId, String status) {

		Optional<Order> oderReturn = Optional.ofNullable(repository.findByUuidString(orderId.toString()));

		if(!oderReturn.isPresent()) {
			throw new UuidNaoEncontrado("Pedido não encontrado para o Uuid informado.");
		}
		repository.updateStatusByUuidString(status,orderId.toString());

		return "Ok";
	}



}
