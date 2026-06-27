package com.grenbui.tickets.mappers;

import com.grenbui.tickets.domain.CreateEventRequest;
import com.grenbui.tickets.domain.CreateTicketTypeRequest;
import com.grenbui.tickets.domain.dtos.CreateEventRequestDto;
import com.grenbui.tickets.domain.dtos.CreateEventResponseDto;
import com.grenbui.tickets.domain.dtos.CreateTicketTypeResponseDto;
import com.grenbui.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest formDto(CreateTicketTypeResponseDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto Dto);

    CreateEventResponseDto toDto(Event event);
}
