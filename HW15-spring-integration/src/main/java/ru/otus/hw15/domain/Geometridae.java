package ru.otus.hw15.domain;

public abstract class Geometridae {

    private final boolean canFly;
    private final String name;

    protected Geometridae(String name, boolean canFly) {
        this.name = name;
        this.canFly = canFly;
    }

    public abstract void act();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{ I " + (canFly ? "CAN" : "CAN'T") + " FLY" +
                " MY NAME IS " + name +
                " }";
    }
}
