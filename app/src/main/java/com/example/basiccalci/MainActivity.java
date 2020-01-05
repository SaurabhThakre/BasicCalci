package com.example.basiccalci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Calculations calculations = new Calculations(this);


    private TextView sub;
    private TextView main;
    private Button ce;
    private Button c;
    private Button div;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button mul;
    private Button four;
    private Button five;
    private Button six;
    private Button plus;
    private Button one;
    private Button two;
    private Button three;
    private Button minus;
    private Button zero;
    private Button equal;
    private Button parent_open;
    private Button parent_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sub = findViewById(R.id.textView);
        main = findViewById(R.id.textView1);
        main.setVisibility(View.INVISIBLE);


        parent_open = findViewById(R.id.button18);
        parent_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.parent_openClicked();
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        parent_close = findViewById(R.id.button19);
        parent_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.parent_closeClicked();
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });


        ce = findViewById(R.id.button16);
        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.ce();
                main.setText("0");
                sub.setText(calculations.calc(calculations.numbers));
            }
        });
        c = findViewById(R.id.button17);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.clear();
                main.setText("0");
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        div = findViewById(R.id.button14);
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.operatorClicked("/");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });


        seven = findViewById(R.id.button7);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("7");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        eight = findViewById(R.id.button8);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("8");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        nine = findViewById(R.id.button9);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("9");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        mul = findViewById(R.id.button12);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.operatorClicked("*");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });


        four = findViewById(R.id.button4);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("4");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        five = findViewById(R.id.button5);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("5");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        six = findViewById(R.id.button6);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("6");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        plus = findViewById(R.id.button10);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.operatorClicked("+");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });


        one = findViewById(R.id.button);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("1");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        two = findViewById(R.id.button2);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("2");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        three = findViewById(R.id.button3);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("3");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });

        minus = findViewById(R.id.button11);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.operatorClicked("-");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });


        zero = findViewById(R.id.button0);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.numberClicked("0");
                main.setText(calculations.getCurrentNumber());
                sub.setText(calculations.calc(calculations.numbers));
            }
        });


        equal = findViewById(R.id.button13);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations.evaluateAnswer();
                main.setText(calculations.answer);
                sub.setText(calculations.answer);
            }
        });
    }
}