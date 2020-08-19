package ru.otus.hw12.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw12.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}