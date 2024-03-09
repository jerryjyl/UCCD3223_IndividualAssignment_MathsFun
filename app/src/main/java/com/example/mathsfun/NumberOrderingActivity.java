package com.example.mathsfun;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mathsfun.general.GeneralFunctionClass;
import com.example.mathsfun.general.GeneralFunctionInterface;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Random;

public class NumberOrderingActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton btNum1, btNum2, btNum3, btNum4, btNum5, btSubmit, restartBT, backBT;
    TextView tvSortOrder, tvUserInput;
    boolean ascOrder;
    GeneralFunctionInterface generalFunc= new GeneralFunctionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_ordering);

        btNum1 = findViewById(R.id.bt_num1);
        btNum2 = findViewById(R.id.bt_num2);
        btNum3 = findViewById(R.id.bt_num3);
        btNum4 = findViewById(R.id.bt_num4);
        btNum5 = findViewById(R.id.bt_num5);
        btSubmit = findViewById(R.id.bt_submit);
        tvSortOrder = findViewById(R.id.tv_sortOrder);
        tvUserInput = findViewById(R.id.tv_userInput);
        restartBT = findViewById(R.id.restart_bt);
        backBT = findViewById(R.id.backBT);

        restartBT.setVisibility(View.GONE);

        // Convert "uniqueNumbers" set into array

        Integer[] uniqueNumArr = generateDistinctRandomNumbers().toArray(new Integer[0]);
        if (uniqueNumArr != null && uniqueNumArr.length == 5) {
            btNum1.setText(uniqueNumArr[0].toString());
            btNum2.setText(uniqueNumArr[1].toString());
            btNum3.setText(uniqueNumArr[2].toString());
            btNum4.setText(uniqueNumArr[3].toString());
            btNum5.setText(uniqueNumArr[4].toString());
        }

        String sortOrder = defineSortOrder();
        if ("Ascending Order".equals(sortOrder)) {
            ascOrder = true;
            tvSortOrder.setText(sortOrder);
        } else if ("Descending Order".equals(sortOrder)) {
            ascOrder = false;
            tvSortOrder.setText(sortOrder);
        }

        btNum1.setOnClickListener(this);
        btNum2.setOnClickListener(this);
        btNum3.setOnClickListener(this);
        btNum4.setOnClickListener(this);
        btNum5.setOnClickListener(this);
        btSubmit.setOnClickListener(this);

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

    private String defineSortOrder(){
        // Create an ArrayList consisting of 'Ascending Order' and 'Descending Order' string elements
        ArrayList<String> sortOrderArrLst = new ArrayList<>();
        sortOrderArrLst.add("Ascending Order");
        sortOrderArrLst.add("Descending Order");

        Random random = new Random();

        // randomly choose one out of 'ascending_order' and 'descending_order'
        return sortOrderArrLst.get(random.nextInt(sortOrderArrLst.size()));
    }

    private ArrayList<Integer> generateDistinctRandomNumbers() {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        Random random = new Random();

        for(int i=0;i<5;i++) {
            int randomNumber = random.nextInt(1000);
            uniqueNumbers.add(i, randomNumber);
        }

        return uniqueNumbers;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() != R.id.bt_submit) {
            MaterialButton button = (MaterialButton) v;
            String buttonText = button.getText().toString();
            String data = tvUserInput.getText().toString();

            if (data == "") data = data + buttonText;
            else data = data + ", " + buttonText;

            tvUserInput.setText(data);

            button.setEnabled(false);
        } else {
            checkAnswer();
            v.setEnabled(false);

            // Call showReplayBT method after 2 seconds
            (new Handler()).postDelayed(this::showReplayBT, 2000);
        }
    }

    private void checkAnswer() {
        String temp = tvUserInput.getText().toString();
        String[] fiveNumbers = temp.split(", ");

        int[] intNumbers = new int[fiveNumbers.length];
        for (int i = 0; i < fiveNumbers.length; i++) {
            intNumbers[i] = Integer.parseInt(fiveNumbers[i].trim());
        }

        // If user inputs exactly 5 integers and not less than
        if (intNumbers.length == 5) {

            // Assumption: set determining variables small2large and large2Small to true by default
            boolean small2Large = true;
            boolean large2Small = true;

            // Check if given input sequence is arranged in ascending order
            for (int i = 0; i < intNumbers.length - 1; i++) {
                if (intNumbers[i] > intNumbers[i + 1]) {

                    // If any number is greater than the next one, it's not in ascending order
                    small2Large = false;
                    break;
                }
            }

            // Check if given input sequence is arranged in descending order
            for (int i = 0; i < intNumbers.length - 1; i++) {
                if (intNumbers[i] < intNumbers[i + 1]) {

                    // If any number is smaller than the next one, it's not in descending order
                    large2Small = false;
                    break;
                }
            }

            //

            // If given input answer sequence is in ascending order
            if (small2Large && !large2Small) {

                // check question requirements: ascending_order OR descending_order
                if (ascOrder) { // if question wants to be arranged in ascending_order
                    Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                }
                else { // if question wants to be arranged in descending_order
                    Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                }

            }

            // If given input answer sequence is in descending order
            else if (!small2Large && large2Small){
                // check question requirements: ascending_order OR descending_order
                if (ascOrder) { // if question wants to be arranged in ascending_order
                    Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                } else { // if question wants to be arranged in descending_order
                    Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                }
            }
            // If given input sequence is not in ascending nor descending order
            else if (!small2Large && !large2Small) {
                Toast.makeText(getApplicationContext(), "Your answer does not match neither ascending nor descending order.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        // If user inputs insufficient amount of integers (less than 5)
        else Toast.makeText(getApplicationContext(), "You have not input exactly five selections!\n" +
                "Please retry again.", Toast.LENGTH_SHORT).show();
    }

    private void showReplayBT() {
        restartBT.setOnClickListener(v -> {
            generalFunc.switchIntent(getApplicationContext(), NumberOrderingActivity.class);
            overridePendingTransition(0, 0);
            finish();
        });

        restartBT.setVisibility(View.VISIBLE);
    }

}