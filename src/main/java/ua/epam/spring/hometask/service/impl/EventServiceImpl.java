package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 */
public class EventServiceImpl implements EventService {

    Collection<Event> eventStorage;
    private AuditoriumService auditoriumService;

    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    void init() {
        System.out.println("********************** EventServiceImpl initialization **********************");
        this.eventStorage = new HashSet();
        eventStorage.add(buildEvent("Ranetki", EventRating.LOW, 100, null, null));
        eventStorage.add(buildEvent("Lord ofo the Rings", EventRating.MID, 350, null, null));
        Event hobbitEvent = buildEvent("Hobbit", EventRating.HIGH, 800, null, null);
        LocalDateTime time = LocalDateTime.of(2017, 12, 10, 10, 00);
        NavigableSet<LocalDateTime> times = new TreeSet();
        times.add(time);
        hobbitEvent.setAirDates(times);
        hobbitEvent.assignAuditorium(time, auditoriumService.getByName("auditorium normal"));
        eventStorage.add(hobbitEvent);
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventStorage.stream().filter(event -> name.equals(event.getName())).findFirst().orElse(null);
    }

    @Override
    public Event save(@Nonnull Event event) {
        eventStorage.add(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventStorage.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventStorage.stream().filter(event -> id.equals(event.getId())).findFirst().orElse(null);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventStorage;
    }

    private Event buildEvent(String name, EventRating rating, double price, NavigableSet airDates,
                             NavigableMap auditoriums) {
        Event event = new Event();
        event.setId((long) new Random().nextInt(100));
        //event.setAirDates(airDates);
        //event.setAuditoriums(auditoriums);
        event.setName(name);
        event.setRating(rating);
        event.setBasePrice(price);
        return event;
    }

    private NavigableSet buildAirDateSet(LocalDateTime...  times) {
        NavigableSet set = new TreeSet();
        for (LocalDateTime time: times) {
            set.add(time);
        }
        return set;
    }
}
