package design.design;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Semaphore tunnel = new Semaphore(5);
        new Shipyard(new ArrayList<Berth>() {{
            add(new Berth(Type.BREAD));
            add(new Berth(Type.BANANA));
            add(new Berth(Type.CLOTHES));
        }}, tunnel).run();
    }
}