package design.design.transport;

import java.io.Serializable;

public class Truck extends Transport {
    private int cargoWeight;

    public String getOthers() {
        return String.format("Вес груза: %d", cargoWeight);
    }

    public Truck(int speed, int probability, int cargoWeight) {
        super("Грузовик", speed, probability);
        this.cargoWeight = cargoWeight;
    }
}
