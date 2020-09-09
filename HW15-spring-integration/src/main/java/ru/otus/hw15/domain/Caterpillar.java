package ru.otus.hw15.domain;

public class Caterpillar extends Geometridae {

    public Caterpillar(String name) {
        this(name, false);
    }

    protected Caterpillar(String name, boolean canFly) {
        super(name, canFly);
    }

    @Override
    public void act() {
        System.out.println(this.toString());
    }

}
