package com.example.mathsfun;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // UI Views
    private ViewPager viewPager;
    private ArrayList<MyModel>modelArrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        loadCards();
    }

    private void loadCards(){
        // init list
        modelArrayList = new ArrayList<>();

        // add items to list
        modelArrayList.add(new MyModel(
                "Comparing Numbers",
                "Quickly decide which number is greater or less than the other!",
                R.drawable.img1));
        modelArrayList.add(new MyModel(
                "Number Ordering",
                "Arrange random numbers in ascending or descending order!",
                R.drawable.img2));
        modelArrayList.add(new MyModel(
                "Composing Numbers",
                "Combine smaller numbers to create the presented number!",
                R.drawable.img3));

        // Setup adapter
        myAdapter = new MyAdapter(this, modelArrayList);

        // Set adapter to viewpager
        viewPager.setAdapter(myAdapter);

        // Set default padding from left / right
        viewPager.setPadding(100, 0, 100, 0);
    }
}