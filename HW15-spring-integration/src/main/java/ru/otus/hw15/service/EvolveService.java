package ru.otus.hw15.service;

import ru.otus.hw15.domain.Butterfly;
import ru.otus.hw15.domain.Caterpillar;

public interface EvolveService {
    Butterfly evolve(Caterpillar caterpillar);
}
