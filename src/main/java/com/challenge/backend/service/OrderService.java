package com.challenge.backend.service;

import com.challenge.backend.model.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {


	Order saveOrder(Long userId, List<Long> productIds,UUID UuidOrder,double totalPrice);


	String updateOrderStatus(UUID orderId, String status);

}
