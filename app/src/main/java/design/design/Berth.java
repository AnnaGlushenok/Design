package design.design;

import android.util.Log;
import android.widget.TextView;

public class Berth {
    private Type type;
    private final int SPEED = 10;
    private MainActivity context;
    private TextView breadBerth, bananaBerth, clothesBerth;

    public Berth(Type type, MainActivity context) {
        this.type = type;
        this.context = context;
        this.breadBerth = context.findViewById(R.id.bread_berth);
        this.bananaBerth = context.findViewById(R.id.banana_berth);
        this.clothesBerth = context.findViewById(R.id.clothes_berth);
    }

    public Type getType() {
        return type;
    }

    public synchronized void load(Ship ship) {
        Log.i("", "load ship " + ship.getType() + " with capacity = " + ship.getCapacity());
        TextView berth = null;
        if (type.equals(Type.BREAD))
            berth = breadBerth;
        else if (type.equals(Type.BANANA))
            berth = bananaBerth;
        else if (type.equals(Type.CLOTHES))
            berth = clothesBerth;

        int capacity = ship.getCapacity();
        while (capacity != 0) {
            capacity -= SPEED;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            context.updateTextView(berth, String.valueOf(capacity));
            Log.d("", "loading... " + ship.getType() + " capacity: " + capacity);
        }
    }
}
