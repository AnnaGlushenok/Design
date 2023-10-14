package design.design.transport;

import java.io.Serializable;

public abstract class Transport implements Serializable, Cloneable {
    private String name;
    protected int speed;
    protected int probability;
    protected int distance;
    protected boolean isStopped;

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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public Transport(String name, int speed, int probability) {
        this.name = name;
        this.speed = speed;
        this.probability = probability;
    }

    @Override
    public Transport clone() {
        try {
            return (Transport) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
