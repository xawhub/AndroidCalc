package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    private ArrayList<Class> classes = new ArrayList<Class>();

    public SliderAdapter(Context context) {
        this.context = context;
        classes.add(SimpleCalc.class);
        classes.add(ScienceCalc.class);
        classes.add(About.class);
    }

    public int[] slide_images = {
            R.drawable.ic_simplecalc,
            R.drawable.ic_scienccalc,
            R.drawable.ic_about,
            R.drawable.ic_logout
    };

    public String[] slide_headings = {
            "Simple Calculator",
            "Scientific Calculator",
            "About",
            "Exit"
    };



    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        Button slideImageView = (Button) view.findViewById(R.id.slide_image);
        final TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);

        slideImageView.setBackgroundResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);

        slideImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slideHeading.getText() == "Exit") {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
                final Intent mIntent = new Intent(context, classes.get(position));
                context.startActivity(mIntent);
            }
        });

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
