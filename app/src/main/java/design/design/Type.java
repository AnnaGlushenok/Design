package design.design;

import java.util.Random;

public enum Type {
    BREAD, BANANA, CLOTHES;

    private static final Random random = new Random();

    public static Type getRandomType() {
        Type[] types = values();
        return types[random.nextInt(types.length)];
    }
}
