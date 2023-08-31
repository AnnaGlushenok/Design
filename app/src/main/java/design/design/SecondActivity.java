package design.design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {
    private LinearLayout container;
    private int countImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        container = findViewById(R.id.container);
        countImages = container.getChildCount();

        ImageView image = findViewById(R.id.image);
        Picasso.get()
                .load("https://avatars.mds.yandex.net/i?id=2b92f91003a7a34974e37b507cfda742cae41fee-8984683-images-thumbs&n=13")
                .into(image);
    }

    public void add(View view) {
        countImages++;
        ImageView image = new ImageView(this);
        Picasso.get()
                .load("https://avatars.mds.yandex.net/i?id=2b92f91003a7a34974e37b507cfda742cae41fee-8984683-images-thumbs&n=13")
                .into(image);
        container.addView(image);
        resize();
    }

    public void remove(View view) {
        if (countImages > 1) {
            countImages--;
            container.removeViewAt(countImages);
        }
        resize();
    }

    private int calculateSize(int count) {
        return getResources().getDisplayMetrics().widthPixels / count;
    }

    private void resize() {
        int size = calculateSize(countImages);
        for (int i = 0; i < countImages; i++)
            container.getChildAt(i).setLayoutParams(new LinearLayout.LayoutParams(size, size));
    }
}