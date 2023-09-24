package design.design;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CompoundView extends ConstraintLayout {
    private Button button;
    private View view, root;
    private TextView text;
    private int radius = 0;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(getResources().getColor(R.color.yellow));
        gradientDrawable.setCornerRadius(radius);

        text.setText(String.valueOf(radius));
        view.setBackground(gradientDrawable);
    }

    public CompoundView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CompoundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CompoundView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CompoundView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        root = inflater.inflate(R.layout.counter, this, true);

        view = root.findViewById(R.id.view);
        text = root.findViewById(R.id.label);
        button = root.findViewById(R.id.button);

        setView();
    }

    private void setView() {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int size = view.getWidth() / 3;
                button.getLayoutParams().width = size;
                button.getLayoutParams().height = size;

                ((MarginLayoutParams) button.getLayoutParams()).bottomMargin = 10;
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) button.getLayoutParams();
                params.bottomToBottom = R.id.view;
                params.endToEnd = R.id.view;
                params.startToStart = R.id.view;
                button.setLayoutParams(params);
            }
        });
    }
}
