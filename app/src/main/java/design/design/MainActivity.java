package design.design;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {
    private Random random = new Random();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Semaphore tunnelSem = new Semaphore(5);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                new Shipyard(new ArrayList<Berth>() {{
                    add(new Berth(Type.BREAD, MainActivity.this));
                    add(new Berth(Type.BANANA, MainActivity.this));
                    add(new Berth(Type.CLOTHES, MainActivity.this));
                }}, tunnelSem, MainActivity.this).run();
            }
        });
        executor.shutdown();
    }

    public void updateTextView(final TextView textView, final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }

}