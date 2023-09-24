package design.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private CompoundView compoundView;
    private TextView text;
    private SharedPreferences sharedPreferences;
    private long timeAppWasMinimized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compoundView = findViewById(R.id.compound_view);
        compoundView.setRadius(getIntent().getIntExtra("msg", 0) + 10);

        text = (TextView) findViewById(R.id.label);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        timeAppWasMinimized = sharedPreferences.getLong("timeAppWasMinimized", 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        float savedTextSize = sharedPreferences.getFloat("textSize", 0);
        if (savedTextSize != 0) {
            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, savedTextSize);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        long currentTime = System.currentTimeMillis();
        long timeDifference = (currentTime - timeAppWasMinimized) / (1000 * 60);
        float textSize = text.getTextSize() + 5 - (2 * timeDifference);
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeAppWasMinimized = System.currentTimeMillis();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("timeAppWasMinimized", timeAppWasMinimized);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("textSize", text.getTextSize());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("destroy");
    }

    public void open(View view) {
        startActivity(new Intent(this, DialogActivity.class).putExtra("msg", compoundView.getRadius()));
    }
}