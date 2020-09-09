package ru.otus.hw15.service;

import org.springframework.stereotype.Service;
import ru.otus.hw15.domain.Caterpillar;
import ru.otus.hw15.integration.Cocoon;

@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "InfiniteLoopStatement", "BusyWait"})
@Service
public class MotherNatureImpl implements MotherNature {
    private final String[] names = new String[]{"CHANDLER", "ROSS", "JOE", "MONICA", "RACHEL", "PHOEBE"};
    private final Cocoon cocoon;

    public MotherNatureImpl(Cocoon cocoon) {
        this.cocoon = cocoon;
    }

    @Override
    public void makeMiracle() {
        while (true) {
            try {
                final var caterpillar = new Caterpillar(names[(int) (Math.random() * (names.length - 1))]);
                caterpillar.act();
                Thread.sleep(2000);
                final var evolve = cocoon.evolve(caterpillar);
                Thread.sleep(2000);
                evolve.act();
                System.out.println("Miracle Done!\n");
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
