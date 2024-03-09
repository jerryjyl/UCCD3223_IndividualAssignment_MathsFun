package com.example.mathsfun;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<MyModel> ModelArrayList;

    public MyAdapter(Context context, ArrayList<MyModel> ModelArrayList) {
        this.context = context;
        this.ModelArrayList = ModelArrayList;
    }

    @Override
    public int getCount() {
        return ModelArrayList.size(); // return list of records/items
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // inflate layout card_item.xml
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);

        // init uid views from card_item.xml
        ImageView bannerTV = view.findViewById(R.id.bannerTV);
        TextView titleTV = view.findViewById(R.id.titleTV);
        TextView descriptionTV = view.findViewById(R.id.descTV);
        Button playBT = view.findViewById(R.id.playBT);

        // get data
        MyModel model = ModelArrayList.get(position);
        final String title = model.getTitle();
        final String description = model.getDescription();
        int image = model.getImage();

        // Set data to UI views
        bannerTV.setImageResource(image);
        titleTV.setText(title);
        descriptionTV.setText(description);

        // Handle card button click
        playBT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(position == 0){
                    // Toast.makeText(context,"Playing 'Comparing Numbers'...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ComparingNumbersActivity.class);
                    context.startActivity(intent);
                }
                else if (position == 1) {
                    // Toast.makeText(context,"Playing 'Number Ordering'...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, NumberOrderingActivity.class);
                    context.startActivity(intent);
                }
                else {
                    // Toast.makeText(context,"Playing 'Composing Numbers'...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ComposingNumbersActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        // Add view to container
        container.addView(view, position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove the view from the container
        container.removeView((View) object);
    }
}