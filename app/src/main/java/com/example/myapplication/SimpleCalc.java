package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.MathContext;


public class SimpleCalc extends AppCompatActivity {

    private static final int [] buttons_num_ids = {
            R.id._0,
            R.id._1,
            R.id._2,
            R.id._3,
            R.id._4,
            R.id._5,
            R.id._6,
            R.id._7,
            R.id._8,
            R.id._9
    };

    private static final int [] buttons_num_val = {
            R.string._0,
            R.string._1,
            R.string._2,
            R.string._3,
            R.string._4,
            R.string._5,
            R.string._6,
            R.string._7,
            R.string._8,
            R.string._9
    };

    double input1 = 0, input2 = 0;

    private int switcher = 0;

    boolean Addition, Subtract, Multiplication, Division, ac_click, decimal;

    String input;

    Button butt_dot, butt_c, butt_ac, butt_add, butt_sub, butt_mul, butt_div, butt_equ, butt_opp;

    TextView screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        screen = (TextView) findViewById(R.id.calc_value);

        butt_dot = (Button) findViewById(R.id.dot);
        butt_c = (Button)findViewById(R.id.c);
        butt_ac = (Button)findViewById(R.id.ac);
        butt_add = (Button)findViewById(R.id.add);
        butt_sub = (Button)findViewById(R.id.sub);
        butt_div = (Button)findViewById(R.id.div);
        butt_mul = (Button)findViewById(R.id.multiply);
        butt_equ = (Button)findViewById(R.id.calc);
        butt_opp = (Button)findViewById(R.id.opposite);

        CharSequence butt_num;

        ac_click = false;

        for (int ids = 0; ids < buttons_num_ids.length;ids++) {
            final Button button = (Button)findViewById(buttons_num_ids[ids]);
            butt_num = getText(buttons_num_val[ids]);
            final String finalButt_num = String.valueOf(butt_num);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        screen.setText(parseOutput(Double.parseDouble((String) screen.getText() + finalButt_num)));
                    }});
        }

        butt_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String screenTmp = (String) screen.getText();
                if (!screenTmp.contains(".")) {
                    if (screen.getText().length() == 0) {
                        screen.setText(String.format("%s0.", screen.getText()));
                    } else {
                        screen.setText(String.format("%s.", screen.getText()));
                    }
                }
            }
        });

        butt_c.setOnClickListener(new DoubleClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onDoubleClick() {
                input1 = 0.0;
                input2 = 0.0;
                screen.setText("");
                butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button));
            }

            @Override
            public void onSingleClick() {
                input2 = 0.0;
                screen.setText("");
            }
        });

        butt_ac.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button));
                if (screen.getText() != "") {
                    //decimal = !decimal;
                    screen.setText("");
                    input1 = 0.0;
                    input2 = 0.0;
                }
            }
        });

        butt_add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                Add();
                butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button_active));
                butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button));
                show();
            }
        });

        butt_sub.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                Sub();
                butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button_active));
                butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button));
                show();
            }
        });

        butt_mul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                Mul();
                butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button_active));
                show();
            }
        });

        butt_div.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                Div();
                butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button));
                butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button_active));
                butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button));
                show();
            }
        });

        butt_equ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (screen.getText() != ""){ equal();}
                equal();
            }
        });

        butt_opp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(switcher);
                if (screen.getText().length() != 0 && Double.parseDouble((String)screen.getText()) != 0) {
                    if (switcher == 0) {
                        input1 = Double.parseDouble((String) screen.getText());
                        input1 = -input1;
                        screen.setText(parseOutput(input1));
                    } else if (switcher == 1) {
                        input2 = Double.parseDouble((String) screen.getText());
                        input2 = -input2;
                        screen.setText(parseOutput(input2));
                    }
                }
            }
        });
    }

    private void show() {
        if (screen.getText().length() != 0) {
            switcher = 1;
            input1 = Double.parseDouble((String) screen.getText());
            //   decimal = false;
            screen.setText(null);
        }
    }

    @SuppressLint("DefaultLocale")
    private String parseOutput(Double result) {
        String output;
        if (result % 1 == 0) {
            output = ("" + String.format("%.0f", result));
        } else {
            output = result.toString();
        }
        return output;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void equal() {
        switcher = 0;
        BigDecimal bdi1;
        BigDecimal bdi2;
        if (screen.getText() != "") {
            if (Addition || Subtract || Multiplication || Division) {
                input2 = Double.parseDouble((String) screen.getText());
            }
            bdi1 = new BigDecimal(input1);
            bdi2 = new BigDecimal(input2);

            if (Addition) {
                BigDecimal result = bdi1.add(bdi2, MathContext.DECIMAL32);
                screen.setText(parseOutput(result.doubleValue()));
                reset();
            }

            if (Subtract) {
                BigDecimal result = bdi1.subtract(bdi2, MathContext.DECIMAL32);
                screen.setText(parseOutput(result.doubleValue()));
                reset();
            }

            if (Multiplication) {
                BigDecimal result = bdi1.multiply(bdi2, MathContext.DECIMAL32);
                screen.setText(parseOutput(result.doubleValue()));
                reset();
            }

            if (Division) {
                if (input2 == 0.0) {
                    screen.setText("");
                } else {
                    BigDecimal result = bdi1.divide(bdi2, MathContext.DECIMAL32);
                    screen.setText(parseOutput(result.doubleValue()));
                }
                reset();
            }
            butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button));
            butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button));
            butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button));
            butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button));
        }
    }

    private void reset() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
    }

    private void Add() {
        Addition = true;
        Subtract = false;
        Multiplication = false;
        Division = false;
    }

    private void Sub() {
        Addition = false;
        Subtract = true;
        Multiplication = false;
        Division = false;
    }

    private void Mul() {
        Addition = false;
        Subtract = false;
        Multiplication = true;
        Division = false;
    }

    private void Div() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = true;
    }
}