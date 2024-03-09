package com.example.mathsfun;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mathsfun.general.GeneralFunctionClass;
import com.example.mathsfun.general.GeneralFunctionInterface;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Random;

public class ComposingNumbersActivity extends AppCompatActivity {

    MaterialButton btBack, btRestart, btSubmit;
    TextView targetNumberTv;
    EditText inputText_1, inputText_2;
    private int randomNum;
    GeneralFunctionInterface generalFunc= new GeneralFunctionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing_numbers);

        btBack = findViewById(R.id.backBT);
        btRestart = findViewById(R.id.restart_bt);
        btSubmit = findViewById(R.id.bt_submit);
        targetNumberTv = findViewById(R.id.targetNumberTV);
        inputText_1 = findViewById(R.id.inputText1);
        inputText_2 = findViewById(R.id.inputText2);

        btRestart.setVisibility(View.GONE);

        // Generate random number and set text to TextView targetNumberTv
        randomNum = new Random().nextInt(1000);
        targetNumberTv.setText(String.valueOf(randomNum));

        // Handle button events
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(inputText_1.getText().toString());
                temp.add(inputText_2.getText().toString());

                int num1, num2, targetNum;
                targetNum = Integer.parseInt(targetNumberTv.getText().toString());
                if (isInteger(temp.get(0))) {
                    num1 = Integer.parseInt(temp.get(0));
                    if (isInteger(temp.get(1))) {
                        num2 = Integer.parseInt(temp.get(1));

                        // Check if composition condition correct
                        if ((num1 + num2) == targetNum) { // If two input correctly makes up targetNum
                            Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Your input does not match the format of an integer number.\n" +
                                "Please retry again.", Toast.LENGTH_SHORT).show();
                    }
                } else Toast.makeText(getApplicationContext(), "Your input does not match the format of an integer number.\n" +
                            "Please retry again.", Toast.LENGTH_SHORT).show();

                btSubmit.setEnabled(false);

                // Call showReplayBT method after 2 seconds
                (new Handler()).postDelayed(ComposingNumbersActivity.this::showReplayBT, 2000);
            }
        });

        btBack.setOnClickListener(e->{
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

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            // Parsing succeeded, so the input is an integer
            return true;
        } catch (NumberFormatException e) {
            // Parsing failed, so the input is not an integer
            return false;
        }
    }

    private void showReplayBT() {
        btRestart.setOnClickListener(v -> {
            generalFunc.switchIntent(getApplicationContext(), ComposingNumbersActivity.class);
            overridePendingTransition(0, 0);
            finish();
        });

        btRestart.setVisibility(View.VISIBLE);
    }

}