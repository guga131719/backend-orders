package com.challenge.backend.api.resource;

import com.challenge.backend.api.dto.OrderDto;
import com.challenge.backend.api.dto.UsuarioDTO;
import com.challenge.backend.exception.RegraNegocioException;
import com.challenge.backend.model.entity.Order;
import com.challenge.backend.service.OrderItemService;
import com.challenge.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderResource {

	private final OrderService service;
	private   Order setOrder;

	private final OrderItemService orderItemService;
	

	@PostMapping
	public ResponseEntity setOrder( @RequestBody OrderDto dto ) {

		try {

			UUID UuidOrder=UUID.randomUUID();

			List<Long> productsIds = dto.getProducts()
					.stream()
					.map(OrderDto.ProductDTO::getId)
					.collect(Collectors.toList());


			double sumvaluesPrice=orderItemService.setOrderItens(dto.getUserId(),productsIds,UuidOrder.toString());

			if(dto.getId()==null){

				setOrder = service.saveOrder(dto.getUserId(),productsIds, UuidOrder,sumvaluesPrice);

			}else{
				setOrder = service.saveOrder(dto.getUserId(),productsIds, dto.getId(),sumvaluesPrice);
			}

			return new ResponseEntity(setOrder, HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}


	@PostMapping("/AlteraStatus")
	@Transactional
	public ResponseEntity<?> AlteraStatus( @RequestBody OrderDto dto ) {

		try {
			service.updateOrderStatus(dto.getId(), dto.getStatus());
			return ResponseEntity.ok("Status do Pedido Atualizado");
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
