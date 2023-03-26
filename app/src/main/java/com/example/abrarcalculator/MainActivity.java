package com.example.abrarcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        display = findViewById(R.id.display);

        setupNumberButtons();
        setupOperatorButtons();
        setupClearButton();
        setupEqualsButton();

    }

    private void setupNumberButtons() {
        int[] buttonIds = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9
        };

        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            final int value = i;
            button.setOnClickListener(view -> {
                String currentDisplay = display.getText().toString();
                if (currentDisplay.equals("0")) {
                    display.setText(String.valueOf(value));
                } else {
                    display.setText(currentDisplay + value);
                }
            });
        }
    }

    private void setupOperatorButtons() {
        Button addButton = findViewById(R.id.button_add);
        Button subtractButton = findViewById(R.id.button_subtract);
        Button multiplyButton = findViewById(R.id.button_multiply);
        Button divideButton = findViewById(R.id.button_divide);

        addButton.setOnClickListener(getOperatorClickListener(Calculator.Operator.ADD));
        subtractButton.setOnClickListener(getOperatorClickListener(Calculator.Operator.SUBTRACT));
        multiplyButton.setOnClickListener(getOperatorClickListener(Calculator.Operator.MULTIPLY));
        divideButton.setOnClickListener(getOperatorClickListener(Calculator.Operator.DIVIDE));
    }

    private View.OnClickListener getOperatorClickListener(final Calculator.Operator operator) {
        return view -> {
            calculator.setFirstOperand(Double.parseDouble(display.getText().toString()));
            calculator.setOperator(operator);
            display.setText("0");
        };
    }

    private void setupClearButton() {
        Button clearButton = findViewById(R.id.button_clear);
        clearButton.setOnClickListener(view -> {
            calculator.reset();
            display.setText("0");
        });
    }

    private void setupEqualsButton() {
        Button equalsButton = findViewById(R.id.button_equals);
        equalsButton.setOnClickListener(view -> {
            try {
                calculator.setSecondOperand(Double.parseDouble(display.getText().toString()));
                double result = calculator.calculate();
                display.setText(String.valueOf(result));
            } catch (ArithmeticException e) {
                display.setText("Error");
            }
        });
    }

}