package design.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View layoutView = findViewById(R.id.layout);
        ViewTreeObserver layoutObserver = layoutView.getViewTreeObserver();
        layoutObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                layoutView.getViewTreeObserver().removeOnPreDrawListener(this);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                if (height > layoutView.getHeight())
                    findViewById(R.id.content_button).setVisibility(View.INVISIBLE);
                else
                    findViewById(R.id.button).setVisibility(View.INVISIBLE);

                return false;
            }
        });
    }
}