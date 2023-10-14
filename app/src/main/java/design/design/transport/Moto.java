package design.design.transport;

public class Moto extends Transport {
    private boolean hasMotorcycle;

    public String getOthers() {
        return hasMotorcycle ? "C коляской" : "Без коляски";
    }

    public Moto(int speed, int probability, boolean hasMotorcycle) {
        super("Мотоцикл", speed, probability);
        this.hasMotorcycle = hasMotorcycle;
    }
}
