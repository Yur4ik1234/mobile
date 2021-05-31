package com.example.first_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;


public class Favorite extends Fragment {

private SliderLayout sliderLayout;
    private String StringGenerated;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        sliderLayout=view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);

        setSliderViews();

        return view;
    }

    private void setSliderViews() {
        for(int i=0; i<5;i++)
        {
            DefaultSliderView  sliderView = new DefaultSliderView(getContext());

            switch (i)
            {
                case 0:
                    sliderView.setImageDrawable(R.drawable.mot);
                    break;
                case 1:
                        sliderView.setImageDrawable(R.drawable.motivation);
                    break;
                case 2:sliderView.setImageDrawable(R.drawable.mor);
                    break;
                case 3:
                        sliderView.setImageDrawable(R.drawable.kari_shea_mrxipnstsba_unsplash);
                    break;
                case 4:
                        sliderView.setImageDrawable(R.drawable.inspirational);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

}