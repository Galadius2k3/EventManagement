package com.hutech.demo.service;

import com.hutech.demo.model.Event;
import com.hutech.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    // Retrieve all events from the database
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Retrieve an event by its id
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Add a new event to the database
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    // Update an existing event
    public Event updateEvent(@NotNull Event event) {
        Event existingEvent = eventRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Event with ID " + event.getId() + " does not exist."));
        existingEvent.setName(event.getName());
        existingEvent.setHostName(event.getHostName());
        existingEvent.setPhone(event.getPhone());
        existingEvent.setStartDate(event.getStartDate());
        existingEvent.setEndDate(event.getEndDate());
        existingEvent.setMinParticipants(event.getMinParticipants());
        existingEvent.setMaxParticipants(event.getMaxParticipants());
        existingEvent.setType(event.getType());
        return eventRepository.save(existingEvent);
    }

    // Delete an event by its id
    public void deleteEventById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalStateException("Event with ID " + id + " does not exist.");
        }
        eventRepository.deleteById(id);
    }
}
