package ua.epam.spring.hometask.service.impl;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 */
@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private Collection<Auditorium> auditoriumStorage;

    @PostConstruct
    private void init() {
        auditoriumStorage = new HashSet<>();
        auditoriumStorage.add(buildAuditorium("auditorium small", 50L));
        auditoriumStorage.add(buildAuditorium("auditorium normal", 80L));
        auditoriumStorage.add(buildAuditorium("auditorium big", 100L));
    }

    @Nonnull
    @Override
    public Collection<Auditorium> getAll() {
        return auditoriumStorage;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumStorage.stream().filter(auditorium -> name.equals(auditorium.getName())).findFirst().orElse(null);
    }

    private Auditorium buildAuditorium(String name, Long numberOfSeats) {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setNumberOfSeats(numberOfSeats);
        return auditorium;
    }
}
