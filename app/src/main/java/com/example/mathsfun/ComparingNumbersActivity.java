package com.example.mathsfun;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mathsfun.general.GeneralFunctionClass;
import com.example.mathsfun.general.GeneralFunctionInterface;
import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class ComparingNumbersActivity extends AppCompatActivity {

    TextView firstNumber, secondNumber, numberRelation;
    MaterialButton greaterThanBT, lesserThanBT, restartBT, backBT;
    private int randomNum1, randomNum2;
    boolean buttonClicked = false;
    GeneralFunctionInterface generalFunc= new GeneralFunctionClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparing_numbers);

        firstNumber = findViewById(R.id.first_number);
        secondNumber = findViewById(R.id.second_number);
        numberRelation = findViewById(R.id.number_relation);
        greaterThanBT = findViewById(R.id.greater_than_bt);
        lesserThanBT = findViewById(R.id.lesser_than_bt);
        restartBT = findViewById(R.id.restart_bt);
        backBT=findViewById(R.id.backBT);

        restartBT.setVisibility(View.GONE);

        // Generate random pairs of Integer Numbers for comparison
        generateRandom();
        SpannableString number_first = new SpannableString(String.valueOf(randomNum1));
        number_first.setSpan(new UnderlineSpan(), 0, number_first.length(), 0);
        firstNumber.setText(number_first);
        secondNumber.setText(String.valueOf(randomNum2));

        // Handle button events
        greaterThanBT.setOnClickListener(v -> {
            if (!buttonClicked) {
                int num1 = Integer.parseInt(firstNumber.getText().toString());
                int num2 = Integer.parseInt(secondNumber.getText().toString());

                if (isLarger(num1, num2)) {
                    //condition measures
                    numberRelation.setText(">");
                    numberRelation.setTextColor(Color.parseColor("#93c47d"));
                    Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                    showReplayBT();
                }
                else {
                    //condition measures
                    numberRelation.setText("<");
                    numberRelation.setTextColor(Color.parseColor("#B30F15"));
                    Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                    showReplayBT();
                }
                buttonClicked = true;
                // Call showReplayBT method after 2 seconds
                (new Handler()).postDelayed(this::showReplayBT, 2000);
            }
        });

        lesserThanBT.setOnClickListener(v -> {
            if (!buttonClicked){
                int num1 = Integer.parseInt(firstNumber.getText().toString());
                int num2 = Integer.parseInt(secondNumber.getText().toString());

                if (!isLarger(num1, num2)) {
                    //condition measures
                    numberRelation.setText("<");
                    numberRelation.setTextColor(Color.parseColor("#B30F15"));
                    Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //condition measures
                    numberRelation.setText(">");
                    numberRelation.setTextColor(Color.parseColor("#93c47d"));
                    Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                }

                buttonClicked = true;
                // Call showReplayBT method after 2 seconds
                (new Handler()).postDelayed(this::showReplayBT, 2000);
            }
        });

        backBT.setOnClickListener(e->{
            generalFunc.switchIntent(getApplicationContext(),MainActivity.class);
            overridePendingTransition(0, 0);
            finish();
        });
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                generalFunc.switchIntent(getApplicationContext(),MainActivity.class);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    private void generateRandom() {
        randomNum1 = new Random().nextInt(1000);
        do {
            randomNum2 = new Random().nextInt(1000);
        }   while (randomNum1 == randomNum2);
    }

    private boolean isLarger(int num1, int num2) {
        return (num1 > num2);
    }

    private void showReplayBT() {
        if (buttonClicked) {
            restartBT.setOnClickListener(v -> {
                generalFunc.switchIntent(getApplicationContext(), ComparingNumbersActivity.class);
                overridePendingTransition(0, 0);
                finish();
            });

            restartBT.setVisibility(View.VISIBLE);
        }
    }
}