package design.design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FirstActivity extends AppCompatActivity {
    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        image = findViewById(R.id.image);
        text = findViewById(R.id.textView);
        text.setVisibility(View.INVISIBLE);
        Picasso.get()
                .load("https://avatars.mds.yandex.net/i?id=2b92f91003a7a34974e37b507cfda742cae41fee-8984683-images-thumbs&n=13")
                .into(image);
    }

    public void show(View view) {
        image.setVisibility(View.VISIBLE);
        text.setVisibility(View.INVISIBLE);
    }

    public void hide(View view) {
        image.setVisibility(View.INVISIBLE);
        text.setVisibility(View.VISIBLE);
    }
}