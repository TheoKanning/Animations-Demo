package theo.animationsdemo.ui.fragment;


import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import theo.animationsdemo.R;


public class AnimatorFragment extends Fragment {

    @Bind(R.id.view_property_animator)
    Button viewProperty;

    @Bind(R.id.object_animator)
    Button object;

    @Bind(R.id.value_animator)
    Button value;

    @OnClick(R.id.view_property_animator)
    public void toggleButtonAlpha() {
        viewProperty.animate()
                .alpha(0)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewProperty.animate()
                                .alpha(1);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    @OnClick(R.id.object_animator)
    public void changeButtonColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(object, "BackgroundColor", color);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.start();
    }

    private boolean valueFillParent = false;


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
    }
}
