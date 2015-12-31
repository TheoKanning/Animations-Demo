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

    @Bind(R.id.morph_button)
    ImageButton morphButton;

    @OnClick(R.id.morph_button)
    public void morph(){
        if(star) {
            morphButton.setImageResource(R.drawable.star_to_square);
        } else {
            morphButton.setImageResource(R.drawable.square_to_star);
        }

        Animatable animatable = (Animatable) morphButton.getDrawable();
        animatable.start();

        star = !star;
    }

    private boolean star = true;

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
