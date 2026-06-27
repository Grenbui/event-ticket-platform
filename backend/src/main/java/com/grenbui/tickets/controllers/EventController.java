package com.grenbui.tickets.controllers;

import com.grenbui.tickets.domain.CreateEventRequest;
import com.grenbui.tickets.domain.dtos.CreateEventRequestDto;
import com.grenbui.tickets.domain.dtos.CreateEventResponseDto;
import com.grenbui.tickets.domain.entities.Event;
import com.grenbui.tickets.mappers.EventMapper;
import com.grenbui.tickets.repositories.EventRepository;
import com.grenbui.tickets.services.EventService;
import com.grenbui.tickets.services.impl.EventServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Controller
@RestController
@RequestMapping(params = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto) {

        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userID = UUID.fromString(jwt.getSubject());

        Event createdEvent = eventService.createdEvent(userID, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);

    }
}
