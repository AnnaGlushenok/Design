package design.design;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Shipyard implements Runnable {
    private static final int[] CAPACITIES = new int[]{10, 50, 100};
    private static final Random random = new Random();
    private List<Berth> berths;
    private Semaphore semaphore;
    private MainActivity context;
    private TextView breadShip, bananaShip, clothesShip;

    public Shipyard(List<Berth> berths, Semaphore semaphore, MainActivity context) {
        this.berths = berths;
        this.semaphore = semaphore;
        this.context = context;
        this.breadShip = context.findViewById(R.id.bread_ship);
        this.bananaShip = context.findViewById(R.id.banana_ship);
        this.clothesShip = context.findViewById(R.id.clothes_ship);
    }

    private Ship getShip() {
        Type type = Type.getRandomType();
        return new Ship(
                CAPACITIES[random.nextInt(CAPACITIES.length)],
                type,
                semaphore,
                (Berth) berths.stream().filter(b -> b.getType().equals(type)).toArray()[0],
                context
        );
    }

    @Override
    public void run() {
        while (true) {
            Log.v("", "new ship");
            Type type = getShip().getType();
            if (type.equals(Type.BREAD))
                context.updateTextView(breadShip, String.valueOf(Integer.parseInt((String) breadShip.getText()) + 1));
            else if (type.equals(Type.BANANA))
                context.updateTextView(bananaShip, String.valueOf(Integer.parseInt((String) bananaShip.getText()) + 1));
            else if (type.equals(Type.CLOTHES))
                context.updateTextView(clothesShip, String.valueOf(Integer.parseInt((String) clothesShip.getText()) + 1));

            new Thread(getShip()).start();
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
