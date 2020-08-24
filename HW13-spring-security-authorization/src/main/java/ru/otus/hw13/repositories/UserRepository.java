package ru.otus.hw13.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw13.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}