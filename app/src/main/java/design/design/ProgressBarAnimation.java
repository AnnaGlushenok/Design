package design.design;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ProgressBarAnimation extends Animation {
    private ProgressBar bar;
    private float from, to;

    public ProgressBarAnimation(ProgressBar bar, float from, float to) {
        super();
        this.bar = bar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        bar.setProgress((int) (from + (to - from) * interpolatedTime));
    }
}
