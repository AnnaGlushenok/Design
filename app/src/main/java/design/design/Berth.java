package design.design;

import android.util.Log;

public class Berth {
    private Type type;
    private final int SPEED = 10;

    public Berth(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public synchronized void load(Ship ship) {
        Log.i("", "load ship " + ship.getType() + " with capacity = " + ship.getCapacity());
        int capacity = ship.getCapacity();
        while (capacity != 0) {
            capacity -= SPEED;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Log.d("", "loading... " + ship.getType() + " capacity: " + capacity);
        }
    }

}
