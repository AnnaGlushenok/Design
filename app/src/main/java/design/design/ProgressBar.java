package design.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ProgressBar extends ConstraintLayout {
    private TextView percent;
    private android.widget.ProgressBar bar;
    private Button btn;
    ProgressBarAnimation anim;

    public ProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setProgress(int val) {
        bar.setProgress(val);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.custom_progress_bar, this, true);

        percent = rootView.findViewById(R.id.percent);
        bar = rootView.findViewById(R.id.progressBar);
        btn = rootView.findViewById(R.id.button);

        btn.setOnClickListener(new OnClickListener() {
            int num;

            @Override
            public void onClick(View view) {
                num = (int) (Math.random() * 100);
                float old = 0;
                try {
                    old = Integer.parseInt((String) percent.getText());
                } catch (NumberFormatException e) {

                }
                anim = new ProgressBarAnimation(ProgressBar.this, old, num);
                anim.setDuration(1000);
                ProgressBar.this.startAnimation(anim);

                bar.setProgress(num);
                percent.setText(String.valueOf(num));
            }
        });
    }
}
