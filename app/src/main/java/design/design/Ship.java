package design.design;

import android.util.Log;

import java.util.concurrent.Semaphore;

public class Ship implements Runnable {
    private int capacity;
    private Type type;
    private Semaphore tunnel;
    private Berth berth;

    public Type getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public Ship(int capacity, Type type, Semaphore tunnel, Berth berth) {
        this.capacity = capacity;
        this.type = type;
        this.tunnel = tunnel;
        this.berth = berth;
    }

    @Override
    public void run() {
        try {
            tunnel.acquire();
            Log.e("", "Ship " + type + " in tunnel");
            berth.load(this);
            tunnel.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
