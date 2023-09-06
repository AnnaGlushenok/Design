package design.design;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ColorView extends ConstraintLayout {
    private String[] colors = {"#F44336", "#E91E63", "#7B1FA2", "#5E35B1", "#1E88E5", "#00ACC1", "#00897B", "#43A047", "#C0CA33", "#FFB300"};
    private Button next, prev;
    private TextView colorIndex;
    private View view;
    int currentColorIndex = 0;

    public ColorView(Context context) {
        super(context);
        init(context);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.color, this, true);

        view = rootView.findViewById(R.id.view);
        prev = rootView.findViewById(R.id.prev_btn);
        next = rootView.findViewById(R.id.next_btn);
        colorIndex = rootView.findViewById(R.id.color_index);

        setColor(colors[0]);

        prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("prev");
                currentColorIndex = (currentColorIndex - 1 + colors.length) % colors.length;
                setColor(colors[currentColorIndex]);
            }
        });

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("next");
                currentColorIndex = (currentColorIndex + 1) % colors.length;
                setColor(colors[currentColorIndex]);
            }
        });
    }

    private void setColor(String colorHex) {
        int color = Color.parseColor(colorHex);
        view.setBackgroundColor(color);
        colorIndex.setText(String.valueOf(currentColorIndex));
    }
}