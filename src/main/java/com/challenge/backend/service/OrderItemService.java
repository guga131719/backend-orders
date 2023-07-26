package com.challenge.backend.service;


import com.challenge.backend.model.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

	OrderItem setOrderItem(OrderItem ordemItem);

	 double  setOrderItens(Long userId, List<Long> productIds, String UuidOrderString);

}
