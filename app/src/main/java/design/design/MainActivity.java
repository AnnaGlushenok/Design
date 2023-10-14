package design.design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import design.design.transport.Car;
import design.design.transport.Moto;
import design.design.transport.Transport;
import design.design.transport.Truck;

public class MainActivity extends AppCompatActivity {
    private TextView textBlock;
    private EditText probability, speed, number;
    private LinearLayout block1, block2;
    private RadioGroup radioGroup;
    private CheckBox checkBox;
    private int idRadioButton;
    private ArrayList<Transport> transports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        block1 = findViewById(R.id.block1);
        block2 = findViewById(R.id.block2);
        speed = findViewById(R.id.speed);
        probability = findViewById(R.id.probability);
        number = findViewById(R.id.number);
        textBlock = findViewById(R.id.textBlock);
        radioGroup = findViewById(R.id.radioGroup);
        checkBox = findViewById(R.id.checkBox);
        transports = new ArrayList<>();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                idRadioButton = i;
                if (i == R.id.truckRadioButton) {
                    textBlock.setText("Вес грузовика:");
                    block1.setVisibility(View.VISIBLE);
                    block2.setVisibility(View.INVISIBLE);
                } else if (i == R.id.carRadioButton) {
                    textBlock.setText("Кол-во людей:");
                    block1.setVisibility(View.VISIBLE);
                    block2.setVisibility(View.INVISIBLE);
                } else if (i == R.id.motoRadioButton) {
                    block1.setVisibility(View.INVISIBLE);
                    block2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void addAndSave(View view) {
        if (idRadioButton == R.id.truckRadioButton) {
            transports.add(new Truck(Integer.parseInt(speed.getText().toString()),
                    Integer.parseInt(probability.getText().toString()),
                    Integer.parseInt(number.getText().toString())));
        } else if (idRadioButton == R.id.carRadioButton) {
            transports.add(new Car(Integer.parseInt(speed.getText().toString()),
                    Integer.parseInt(probability.getText().toString()),
                    Integer.parseInt(number.getText().toString())));
        } else if (idRadioButton == R.id.motoRadioButton) {
            transports.add(new Moto(Integer.parseInt(speed.getText().toString()),
                    Integer.parseInt(probability.getText().toString()),
                    checkBox.isChecked()));
        }
        clear();
    }

    public void save(View view) {
//        transports = new ArrayList<Transport>() {{
//            add(new Moto(50, 2, false));
//            add(new Moto(60, 1, true));
//            add(new Truck(60, 2, 90));
//            add(new Car(45, 3, 4));
//        }};
        startActivity(new Intent(this, RaceActivity.class)
                .putExtra("transports", transports)
                .putExtra("circleLength",
                        Integer.parseInt(
                                ((EditText) findViewById(R.id.circleLength))
                                        .getText()
                                        .toString()
                        )));
    }

    private void clear() {
        radioGroup.clearCheck();
        speed.getText().clear();
        probability.getText().clear();
        number.getText().clear();
        checkBox.setChecked(false);
    }
}