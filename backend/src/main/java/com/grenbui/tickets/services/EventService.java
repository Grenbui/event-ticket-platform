package com.grenbui.tickets.services;

import com.grenbui.tickets.domain.CreateEventRequest;
import com.grenbui.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createdEvent(UUID organizerId, CreateEventRequest event);
}
