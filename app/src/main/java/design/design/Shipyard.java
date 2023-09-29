package design.design;

import android.util.Log;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Shipyard implements Runnable {
    private static final int[] CAPACITIES = new int[]{10, 50, 100};
    private static final Random random = new Random();
    private List<Berth> berths;
    private Semaphore semaphore;

    public Shipyard(List<Berth> berths, Semaphore semaphore) {
        this.berths = berths;
        this.semaphore = semaphore;
    }

    private Ship getShip() {
        Type type = Type.getRandomType();
        return new Ship(
                CAPACITIES[random.nextInt(CAPACITIES.length)],
                type,
                semaphore,
                (Berth) berths.stream().filter(b -> b.getType().equals(type)).toArray()[0]
        );
    }

    @Override
    public void run() {
        while (true) {
            Log.v("", "new ship");
            new Thread(getShip()).start();
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
