package design.design.transport;

import java.io.Serializable;

public abstract class Transport implements Serializable {
    private String name;
    protected int speed;
    protected int probability;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getProbability() {
        return probability;
    }


    public String getOthers() {
        return "";
    }


    public Transport(String name, int speed, int probability) {
        this.name = name;
        this.speed = speed;
        this.probability = probability;
    }
}
