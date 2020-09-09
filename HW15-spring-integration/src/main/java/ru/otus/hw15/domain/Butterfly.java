package ru.otus.hw15.domain;

public class Butterfly extends Caterpillar {
    private static final String[] colours = new String[]{"RED", "GREEN", "BLUE", "YELLOW", "PINK", "BLACK", "WHITE"};

    public Butterfly(Caterpillar caterpillar) {
        super(evolveName(caterpillar.getName()), true);
    }

    @Override
    public void act() {
        System.out.println(this.toString());
    }

    public static String evolveName(String name) {
        return colours[(int) (Math.random() * (colours.length - 1))] + "_" + name;
    }
}
