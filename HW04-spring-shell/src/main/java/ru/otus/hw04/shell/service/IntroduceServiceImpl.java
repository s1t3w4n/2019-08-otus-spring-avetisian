package ru.otus.hw04.shell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.IntroduceService;

import java.util.Objects;

@Service
public class IntroduceServiceImpl implements IntroduceService {
    private String name;
    private String surname;

    @Override
    public boolean isIntroduced() {
        return Objects.nonNull(name) && Objects.nonNull(surname);
    }

    @Override
    public String introduce(String data) {
        if (Objects.nonNull(data)) {
            setData(data);
        }
        return Objects.isNull(name) ? "print.name" : "print.surname";
    }

    @Override
    public String askName() {
        return name;
    }

    @Override
    public String askSurname() {
        return surname;
    }

    private void setData(String data) {
        if (Objects.isNull(name)) {
            name = data;
        } else {
            surname = data;
        }
    }
}
