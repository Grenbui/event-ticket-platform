package com.grenbui.tickets.services.impl;

import com.grenbui.tickets.domain.CreateEventRequest;
import com.grenbui.tickets.domain.entities.Event;
import com.grenbui.tickets.domain.entities.TicketType;
import com.grenbui.tickets.domain.entities.User;
import com.grenbui.tickets.exceptions.UserNotFoundException;
import com.grenbui.tickets.repositories.EventRepository;
import com.grenbui.tickets.repositories.UserRepository;
import com.grenbui.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createdEvent(UUID organizerId, CreateEventRequest event) {
        User origanizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID '%s' not found", organizerId)
                ));

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(ticektType -> {
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setName(ticektType.getName());
            ticketTypeToCreate.setPrice(ticektType.getPrice());
            ticketTypeToCreate.setDescription(ticketTypeToCreate.getDescription());
            ticketTypeToCreate.setTotalAvailable(ticketTypeToCreate.getTotalAvailable());

            return ticketTypeToCreate;
        }).toList();


        Event eventToCreate = new Event();
        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(origanizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);

    }
}
