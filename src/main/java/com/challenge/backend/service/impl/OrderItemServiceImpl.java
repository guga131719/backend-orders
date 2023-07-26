package com.challenge.backend.service.impl;


import com.challenge.backend.integration.ProductApi;
import com.challenge.backend.integration.domain.Product;
import com.challenge.backend.model.entity.OrderItem;
import com.challenge.backend.model.repository.OrderItemRepository;
import com.challenge.backend.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemRepository repository;
	double precoTotalCarrinho=0;
	public OrderItemServiceImpl(
			OrderItemRepository repository) {
		super();
		this.repository = repository;

	}

	@Override
	@Transactional
	public OrderItem setOrderItem(OrderItem ordemItem) {

		return repository.save(ordemItem);
	}


	public double  setOrderItens(Long userId, List<Long> productIds, String UuidOrderString) {

		double returnSomaValue= 0;
		for (Long productId : productIds) {

			System.out.println("ID do produto: " + productId);
			ProductApi productApi = new ProductApi();
			Product returnOrserApi = productApi.getProductById(productId);


			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(returnOrserApi.getPrice());
			orderItem.setAmount(1);
			orderItem.setOrder_uuid_string(UuidOrderString);
			orderItem.setIdProduct(Math.toIntExact(productId));
			orderItem.setPartialAmount(returnOrserApi.getPrice());


			this.setOrderItem(orderItem);
			returnSomaValue+=returnOrserApi.getPrice();
		}

		return returnSomaValue;
	}

}
