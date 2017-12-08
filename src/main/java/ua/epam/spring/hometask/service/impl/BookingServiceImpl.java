package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class BookingServiceImpl implements BookingService {
    
    private DiscountService discountService;

    private Set<Ticket> bookedTickets = new HashSet<>();
    
    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double ticketsPriсe = event.getBasePrice() * seats.size();
        byte discountPercent = discountService.getDiscount(user, event, dateTime, seats.size());
        ticketsPriсe *= (100 - discountPercent)/100.0;
        return ticketsPriсe;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        bookedTickets.addAll(tickets);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return bookedTickets;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}
