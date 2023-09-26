package design.design;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private volatile int duration = 0, toast = 0;
    private ExecutorService pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView label = findViewById(R.id.label);
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        label.setText(String.valueOf(duration));
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, String.valueOf(duration), Toast.LENGTH_SHORT).show();
                        toast++;
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Surprise", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        pool = Executors.newFixedThreadPool(3);
        pool.execute(new TimerTask() {
            @Override
            public void run() {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        duration++;
                        handler.sendEmptyMessage(1);
                    }
                }, 0, 1000);
            }
        });
        pool.execute(new TimerTask() {
            @Override
            public void run() {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(2);
                    }
                }, 0, 10000);
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (toast == 4) {
                        handler.sendEmptyMessage(3);
                        toast = 0;
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pool.shutdown();
    }
}