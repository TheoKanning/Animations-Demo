package theo.animationsdemo.ui.fragment;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import theo.animationsdemo.R;


public class AnimatorFragment extends Fragment {

    @Bind(R.id.animator_view)
    View animatorView;

    @OnClick(R.id.view_property_animator)
    public void toggleViewAlpha() {

        float alpha;
        if (viewTransparent) {
            alpha = 1;
        } else {
            alpha = 0;
        }
        viewTransparent = !viewTransparent;

        animatorView.animate().alpha(alpha);
    }

    /**
     * Changes object button to random color using ObjectAnimator.
     */
    @OnClick(R.id.object_animator)
    public void changeViewColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(animatorView, "BackgroundColor", color);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.start();
    }

    /**
     * Changes view height and width to random values using ValueAnimator.
     */
    @OnClick(R.id.value_animator)
    public void changeViewHeight() {
        Random random = new Random();
        int height = random.nextInt(maxViewHeight);
        int width = random.nextInt(maxViewWidth);
        ValueAnimator heightAnimator = ValueAnimator.ofInt(animatorView.getHeight(), height);
        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatorView.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                animatorView.requestLayout();
            }
        });

        ValueAnimator widthAnimator = ValueAnimator.ofInt(animatorView.getWidth(), width);
        widthAnimator.setStartDelay(50);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatorView.getLayoutParams().width = (Integer) animation.getAnimatedValue();
                animatorView.requestLayout();
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(heightAnimator, widthAnimator);
        set.start();
    }

    private boolean viewTransparent = false;
    private int maxViewHeight;
    private int maxViewWidth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animator, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getActivity().setTitle(R.string.animators);

        /**
         * Get initial dimensions of animatedView. Setting an OnPreDrawListener is the best way to
         * get view dimensions as soon as possible, which is very helpful when initializing
         * transitions.
         */
        animatorView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                animatorView.getViewTreeObserver().removeOnPreDrawListener(this);
                maxViewHeight = animatorView.getHeight();
                maxViewWidth = animatorView.getWidth();

                return true;
            }
        });
    }
}
