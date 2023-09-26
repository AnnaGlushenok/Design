package design.design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void update(View view) {
        startActivity(new Intent(this, MainActivity.class).putExtra("msg", getIntent().getIntExtra("msg", 0)));
    }

    public void cancel(View view) {
        startActivity(new Intent(this, MainActivity.class).putExtra("msg", getIntent().getIntExtra("msg", 0) - 10));
    }
}