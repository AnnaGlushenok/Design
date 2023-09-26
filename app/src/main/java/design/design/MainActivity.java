package design.design;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CompoundView compoundView;
    private TextView text;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compoundView = findViewById(R.id.compound_view);
        compoundView.setRadius(getIntent().getIntExtra("msg", 0) + 10);

        text = (TextView) findViewById(R.id.label);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        updateFontSizePreference(- 1);
        sharedPreferences.edit()
                .putLong(Saves.TIME.name(), 0)
                .apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        float newTextSize = sharedPreferences.getFloat(Saves.TEXT_SIZE.name(), 0);
        if (newTextSize == - 1) {
            updateFontSizePreference(text.getTextSize());
            return;
        }
        updateFontSizePreference(newTextSize + 2);
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize + 2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        float textSize = sharedPreferences.getFloat(Saves.TEXT_SIZE.name(), 0);
        long time = sharedPreferences.getLong(Saves.TIME.name(), 0);
        if (time == 0)
            return;

        float newTextSize = Math.abs(textSize - (float) (System.currentTimeMillis() - time) / 30000);
        text.setTextSize(newTextSize);
        updateFontSizePreference(newTextSize);
    }

    @Override
    protected void onPause() {
        super.onPause();
        float newTextSize = sharedPreferences.getFloat(Saves.TEXT_SIZE.name(), 0) + 5;
        updateFontSizePreference(newTextSize);

        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sharedPreferences.edit()
                .putLong(Saves.TIME.name(), System.currentTimeMillis())
                .apply();
    }

    @Override
    protected void onDestroy() {
        updateFontSizePreference(text.getTextSize());
        super.onDestroy();
    }

    private void updateFontSizePreference(float newSize) {
        sharedPreferences.edit()
                .putFloat(Saves.TEXT_SIZE.name(), newSize)
                .apply();
    }

    public void open(View view) {
        startActivity(new Intent(this, DialogActivity.class).putExtra("msg", compoundView.getRadius()));
    }
}

enum Saves {
    TEXT_SIZE, TIME
}