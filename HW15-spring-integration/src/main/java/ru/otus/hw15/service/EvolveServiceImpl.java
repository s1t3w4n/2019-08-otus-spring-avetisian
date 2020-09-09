package ru.otus.hw15.service;

import org.springframework.stereotype.Service;
import ru.otus.hw15.domain.Butterfly;
import ru.otus.hw15.domain.Caterpillar;

@Service
public class EvolveServiceImpl implements EvolveService {
    @Override
    public Butterfly evolve(Caterpillar caterpillar) {
        System.out.println("Evolving...");
        return new Butterfly(caterpillar);
    }
}
