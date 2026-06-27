package com.grenbui.tickets.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeRequestDto {

    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;
}
