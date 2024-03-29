package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private TextView screen;
    private int op1 = 0;
    private int op2 = 0;
    private String operator = "";
    private boolean isOp1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);

        Button btnEgal = (Button)findViewById(R.id.btnEgal);
        btnEgal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compute();
            }
        });
    }

    private void updateDisplay() {
        int v = isOp1 ? op1 : op2;
        screen.setText(String.format("%d", v));
    }

    public void compute() {
        if (!isOp1) {
            if (operator.equals("+")) {
                op1 = op1 + op2;
            } else if (operator.equals("-")) {
                op1 = op1 - op2;
            } else if (operator.equals("*")) {
                op1 = op1 * op2;
            } else if (operator.equals("/")) {
                if (op2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_LONG).show();
                    return;
                }
                op1 = op1 / op2;
            } else {
                return;
            }
            op2 = 0;
            isOp1 = true;
            updateDisplay();
        }
    }
    public void clear(View view) {
        op1 = 0;
        op2 = 0;
        operator = "";
        isOp1 = true;
        updateDisplay();
    }

    public void setOperator(View v) {
        if (v.getId() == R.id.btnPlus) {
            operator = "+";
        } else if (v.getId() == R.id.btnMoins) {
            operator = "-";
        } else if (v.getId() == R.id.btnFois) {
            operator = "*";
        } else if (v.getId() == R.id.btnDiv) {
            operator = "/";
        } else {
            Toast.makeText(this, "Opérateur non reconnu", Toast.LENGTH_LONG).show();
            return;
        }
        isOp1 = false;
    }

    public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1) {
                op1 = op1 * 10 + val;
            } else {
                op2 = op2 * 10 + val;
            }
            updateDisplay();
        } catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée", Toast.LENGTH_LONG).show();
        }
    }
}
