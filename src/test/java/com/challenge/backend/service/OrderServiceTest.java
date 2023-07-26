package com.challenge.backend.service;

import com.challenge.backend.exception.ErroAutenticacao;
import com.challenge.backend.exception.RegraNegocioException;
import com.challenge.backend.model.entity.Order;
import com.challenge.backend.model.entity.Usuario;
import com.challenge.backend.model.repository.OrderRepository;
import com.challenge.backend.model.repository.UsuarioRepository;
import com.challenge.backend.service.impl.OrderServiceImpl;
import com.challenge.backend.service.impl.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class OrderServiceTest {

	@SpyBean
	OrderServiceImpl service;
	
	@MockBean
	OrderRepository repository;
	

	@Test
	public void deveSalvarUmOrder() {
		UUID uuidOrder = UUID.randomUUID();


		Mockito.doReturn(
				Order.builder()
						.id(uuidOrder)
						.userId(1)
						.uuid_string(uuidOrder.toString())
						.totalPrice(2332.434)
						.build()
		).when(service).saveOrder(Mockito.anyLong(), Mockito.anyList(), Mockito.any(), Mockito.anyDouble());

		List<Long> listaInteiros = new ArrayList<>();
		listaInteiros.add(10L);

		Order orderSalvo = service.saveOrder(1L, listaInteiros, uuidOrder, 2332.434);


		Assertions.assertThat(orderSalvo).isNotNull();
		Assertions.assertThat(orderSalvo.getId()).isEqualTo(uuidOrder);
		Assertions.assertThat(orderSalvo.getTotalPrice()).isEqualTo(2332.434);
		Assertions.assertThat(orderSalvo.getStatus()).isEqualTo("PENDING");
		Assertions.assertThat(orderSalvo.getUserId()).isEqualTo(1);
	}
	@Test
	public void deveAlterarStatusUmaOrder() {
		UUID orderId = UUID.randomUUID();
		String status = "COMPLETED";


		Mockito.doNothing().when(service).updateOrderStatus(Mockito.any(UUID.class), Mockito.anyString());


		service.updateOrderStatus(orderId, status);


		Mockito.verify(service).updateOrderStatus(orderId, status);
	}
}
