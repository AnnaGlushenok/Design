package design.design;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import design.design.transport.Transport;

public class RaceActivity extends AppCompatActivity {
    private ArrayList<Transport> TRANSPORTS, transports;
    private HashSet<Transport> winners;
    private Handler handler;
    private TextView win;
    private TableLayout table;
    private Runnable runnable;
    private int circleLength;
    private int race;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race);
        table = findViewById(R.id.table);
        win = findViewById(R.id.win);
        handler = new Handler();
        TRANSPORTS = (ArrayList<Transport>) getIntent().getSerializableExtra("transports");
        transports = (ArrayList<Transport>) TRANSPORTS.stream().map(Transport::clone).collect(Collectors.toList());
        winners = new HashSet<>();
        circleLength = getIntent().getIntExtra("circleLength", 0) * 100;
        handler = new Handler();
        table = findViewById(R.id.table);
        runnable = new Runnable() {
            public void run() {
                clearTable();
                newCircle();
            }
        };
        handler.post(runnable);
    }

    public void newCircle(View view) {
        transports = (ArrayList<Transport>) TRANSPORTS.stream().map(Transport::clone).collect(Collectors.toList());
        winners.clear();
        win.setVisibility(View.INVISIBLE);
        handler.post(runnable);
    }

    private void newCircle() {
        race = transports
                .stream()
                .filter(t -> !t.isStopped())
                .toArray()
                .length;
        for (Transport transport : transports) {
            if (!transport.isStopped())
                transport.setDistance(transport.getDistance() + transport.getSpeed());
            if (System.currentTimeMillis() % (10 - transport.getProbability()) == 0
                    && !transport.isStopped()) {
                Log.wtf("", transport.getName() + " прокол колеса");
                transport.setStopped(true);
            }
            if (transport.getDistance() >= circleLength) {
                transport.setStopped(true);
                winners.add(transport);
            }
        }
        transports.forEach(t ->
                addRow(t.getName(),
                        String.valueOf(t.getSpeed()),
                        String.valueOf(t.getProbability()),
                        t.getOthers(),
                        String.valueOf(t.getDistance())));
        race = transports
                .stream()
                .filter(t -> !t.isStopped())
                .toArray()
                .length;

        if (race > 0)
            handler.postDelayed(runnable, 2000);
        else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showWinners();
                }
            });
        }
    }

    private void showWinners() {
        clearTable();
        win.setVisibility(View.VISIBLE);
        winners.forEach(w ->
                addRow(w.getName(),
                        String.valueOf(w.getSpeed()),
                        String.valueOf(w.getProbability()),
                        w.getOthers(),
                        String.valueOf(w.getDistance())));
    }

    private void clearTable() {
        int count = table.getChildCount();
        for (int i = 0; i < count; i++)
            table.removeView(table.getChildAt(1));
    }

    private void addRow(String transport, String speed, String probability, String
            others, String distance) {
        TableRow row = new TableRow(this);
        TextView transportTextView = new TextView(this);
        TextView speedTextView = new TextView(this);
        TextView probabilityTextView = new TextView(this);
        TextView othersTextView = new TextView(this);
        TextView distanceTextView = new TextView(this);

        transportTextView.setText(transport);
        speedTextView.setText(speed);
        probabilityTextView.setText(probability);
        othersTextView.setText(others);
        distanceTextView.setText(distance);

        speedTextView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        transportTextView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        probabilityTextView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        othersTextView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        distanceTextView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        row.setBackgroundResource(R.drawable.border);
        row.setPadding(10, 10, 10, 10);
        row.addView(transportTextView);
        row.addView(speedTextView);
        row.addView(probabilityTextView);
        row.addView(othersTextView);
        row.addView(distanceTextView);

        handler.post(new Runnable() {
            @Override
            public void run() {
                table.addView(row);
            }
        });
//        table.post(new Runnable() {
//            @Override
//            public void run() {
//                table.addView(row);
//            }
//        });
    }
}