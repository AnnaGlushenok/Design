package design.design;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import design.design.transport.Transport;

public class RaceActivity extends AppCompatActivity {
    private ArrayList<Transport> transports;
    private Handler handler;
    private TableLayout table;
    private int circleLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race);
        transports = (ArrayList<Transport>) getIntent().getSerializableExtra("transports");
        circleLength = (int) getIntent().getSerializableExtra("circleLength");
        handler = new Handler();
        table = findViewById(R.id.table);
        transports.forEach(t ->
                addRow(t.getName(),
                        String.valueOf(t.getSpeed()),
                        String.valueOf(t.getProbability()),
                        t.getOthers(),
                        "8"));
    }

    private void addRow(String transport, String speed, String probability, String others, String distance) {
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

        row.addView(transportTextView);
        row.addView(speedTextView);
        row.addView(probabilityTextView);
        row.addView(othersTextView);
        row.addView(distanceTextView);

        table.addView(row);
    }
}