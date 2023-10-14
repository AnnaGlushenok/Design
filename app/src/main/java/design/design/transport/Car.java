package design.design.transport;

public class Car extends Transport {
    private int countPassengers;

    public String getOthers() {
        return String.format("Пассажиры: %d", countPassengers);
    }

    public Car(int speed, int probability, int countPassengers) {
        super("Легковая", speed, probability);
        this.countPassengers = countPassengers;
    }
}
