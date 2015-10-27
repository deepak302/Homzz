package com.mentobile.homzz;

import android.app.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by user on 10/20/2015.
 */
public class ImageSliderPageAdapter extends PagerAdapter {

    private final Activity activity;
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public ImageSliderPageAdapter(Activity activity, ArrayList<Integer> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private View view = null;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_image_slider, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_slider_image);
        imageView.setBackgroundResource(arrayList.get(position));

        TextView textView = (TextView) view.findViewById(R.id.img_slider_counter);
        textView.setText((position + 1) + "/" + getCount());

        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
