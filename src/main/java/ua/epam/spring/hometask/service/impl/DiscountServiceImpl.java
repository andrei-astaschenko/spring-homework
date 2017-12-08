package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 */
public class DiscountServiceImpl implements DiscountService {

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        Collection<Byte> discounts = new LinkedList<>();
        discounts.add(getBirthdayDiscount(user, event, airDateTime));
        discounts.add(getTenTicketDiscount());
        return discounts.stream().max(Byte::compareTo).orElse((byte) 0);
    }

    private byte getBirthdayDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime) {
        if(event.getAirDates().contains(airDateTime)
            && user.getBirthday().isBefore(ChronoLocalDate.from(airDateTime.plusDays(5)))
            && user.getBirthday().isAfter(ChronoLocalDate.from(airDateTime.minusDays(5)))) {

            return 5;
        }
        return 0;
    }

    private byte getTenTicketDiscount() {
        return 0;
    }
}
