package ua.epam.spring.hometask.service.impl;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    private Collection<User> userStorage;

    @PostConstruct
    void initSpec() {
        System.out.println("********************** UserServiceImpl initialization **********************");
        Set<User> users = new HashSet();
        users.add(buildUser("Bobby", "Red", "bob@mail.ru"));
        users.add(buildUser("Jimmy", "Blue", "jimmy@mail.ru"));
        users.add(buildUser("Frank", "Grey", "frank@mail.ru"));
        users.add(buildUser("Elizabet", "Pink", "elizabet@mail.ru"));
        userStorage = users;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userStorage.stream().filter(user -> email.equals(user.getEmail())).findFirst().orElse(null);
    }

    @Override
    public User save(@Nonnull User user) {
        userStorage.add(user);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        userStorage.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userStorage.stream().filter(user -> id.equals(user.getId())).findFirst().orElse(null);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userStorage;
    }

    private User buildUser(String firstName, String lastName, String email) {
        User user = new User();
        user.setId((long) new Random().nextInt(100));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthday(LocalDate.now());
        return user;
    }
}
