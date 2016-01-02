package theo.animationsdemo.ui.fragment;


import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import theo.animationsdemo.R;

public class MorphingFragment extends Fragment {

    @Bind(R.id.star_button)
    ImageButton starButton;

    @OnClick(R.id.star_button)
    public void morphStarButton(){
        if(star) {
            starButton.setImageResource(R.drawable.star_to_square);
        } else {
            starButton.setImageResource(R.drawable.square_to_star);
        }

        Animatable animatable = (Animatable) starButton.getDrawable();
        animatable.start();

        star = !star;
    }

    @Bind(R.id.plus_button)
    ImageButton plusButton;

    @OnClick(R.id.plus_button)
    public void morphPlusButton(){
        if(plus) {
            plusButton.setImageResource(R.drawable.plus_to_check);
        } else {
            plusButton.setImageResource(R.drawable.check_to_plus);
        }

        Animatable animatable = (Animatable) plusButton.getDrawable();
        animatable.start();

        plus = !plus;
    }

    private boolean star = true;
    private boolean plus = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_morphing, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
}
