package design.design;

import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Semaphore;

public class Ship implements Runnable {
    private int capacity;
    private Type type;
    private Semaphore tunnelSem;
    private Berth berth;
    private MainActivity context;
    private TextView tunnel;
    private TextView breadShip, bananaShip, clothesShip;

    public Type getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public Ship(int capacity, Type type, Semaphore tunnelSem, Berth berth, MainActivity context) {
        this.capacity = capacity;
        this.type = type;
        this.tunnelSem = tunnelSem;
        this.berth = berth;
        this.context = context;
        this.tunnel = context.findViewById(R.id.tunnel);
        this.breadShip = context.findViewById(R.id.bread_ship);
        this.bananaShip = context.findViewById(R.id.banana_ship);
        this.clothesShip = context.findViewById(R.id.clothes_ship);
    }

    @Override
    public void run() {
        try {
            tunnelSem.acquire();
            if (type.equals(Type.BREAD))
                context.updateTextView(breadShip, String.valueOf(Integer.parseInt((String) breadShip.getText()) - 1));
            else if (type.equals(Type.BANANA))
                context.updateTextView(bananaShip, String.valueOf(Integer.parseInt((String) bananaShip.getText()) - 1));
            else if (type.equals(Type.CLOTHES))
                context.updateTextView(clothesShip, String.valueOf(Integer.parseInt((String) clothesShip.getText()) - 1));

            context.updateTextView(tunnel, String.valueOf(Integer.parseInt(String.valueOf(tunnel.getText())) + 1));
            Log.e("", "Ship " + type + " in tunnel");
            berth.load(this);

            tunnelSem.release();
            Log.e("", "Ship " + type + " leave tunnel");
            context.updateTextView(tunnel, String.valueOf(Integer.parseInt(String.valueOf(tunnel.getText())) - 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
