package com.challenge.backend.api.dto;


import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long userId;
    private UUID id;
    private String status;
    private List<ProductDTO> products;

    @Getter
    @Setter
    public static class ProductDTO {
        private Long id;
        private Long amount;
    }
}
