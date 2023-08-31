package design.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void first(View view) {
        startActivity(new Intent(this, FirstActivity.class));
    }

    public void second(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void third(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }
}