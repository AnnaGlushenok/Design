package design.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private CompoundView compoundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compoundView = findViewById(R.id.compound_view);
        compoundView.setRadius(getIntent().getIntExtra("msg", 0) + 10);
    }

    public void open(View view) {
        startActivity(new Intent(this, DialogActivity.class).putExtra("msg", compoundView.getRadius()));
    }
}