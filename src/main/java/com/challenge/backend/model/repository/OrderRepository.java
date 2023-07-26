package com.challenge.backend.model.repository;

import com.challenge.backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

     @Query(value = "SELECT o FROM Order o WHERE o.uuid_string = :uuid_string")
      Order findByUuidString(  @Param("uuid_string") String uuid_string);


    @Modifying
    @Query(value = "UPDATE Order o SET o.status = :status WHERE o.uuid_string = :uuid_string")
    int updateStatusByUuidString(String status, String uuid_string);


	
}
