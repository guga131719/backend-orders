package com.challenge.backend.model.repository;

import com.challenge.backend.model.entity.Order;
import com.challenge.backend.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


   /* @Query(value = "SELECT o FROM orderitem o WHERE o.order_uuid_string = :order_uuid_string")
    List<OrderItem> findByUuidString(@Param("order_uuid_string") String uuid_string);*/


}
