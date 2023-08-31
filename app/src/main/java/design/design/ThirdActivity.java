package design.design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ThirdActivity extends AppCompatActivity {
    ImageView image;
    TextView text;
    Animation animation;
    float alpha = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        image = findViewById(R.id.image);
        text = findViewById(R.id.textView2);
        Picasso.get()
                .load("https://avatars.mds.yandex.net/i?id=2b92f91003a7a34974e37b507cfda742cae41fee-8984683-images-thumbs&n=13")
                .into(image);
    }

    public void add(View view) {
        if (alpha == 1) {
            text.setText("Ярче уже не будет");
            return;
        }
        changeAlpha(alpha, Math.round((alpha + 0.1) * 100) / 100.0f);
    }

    public void remove(View view) {
        if (alpha == 0) {
            text.setText("Прозрачнее уже не будет");
            return;
        }
        changeAlpha(alpha, Math.round((alpha - 0.1) * 100) / 100.0f);
    }

    public void changeAlpha(float oldAlpha, float newAlpha) {
        alpha = newAlpha;

        animation = new AlphaAnimation(oldAlpha, newAlpha);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());

        image.startAnimation(animation);
        image.setAlpha(alpha);
        text.setText("alpha = " + alpha);
    }
}