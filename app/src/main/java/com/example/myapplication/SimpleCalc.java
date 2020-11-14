package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class SimpleCalc extends AppCompatActivity {

    double input1 = 0, input2 = 0;

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

    TextView screen;

    boolean Addition, Subtract, Multiplication, Division, empty, decimal, ac_click;

    Button butt_dot, butt_c, butt_ac, butt_add, butt_sub, butt_mul, butt_div, butt_equ, butt_opp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        screen = (TextView)findViewById(R.id.calc_value);

        butt_dot = (Button)findViewById(R.id.dot);
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
                        screen.setText(screen.getText() + finalButt_num);
                    }});
        }

        butt_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String screenTmp = (String) screen.getText();
                if (decimal) {
                    //do nothing or you can show the error
                } else /*if (!screenTmp.contains("."))*/ {
                    if(screen.getText().length() == 0){
                        screen.setText(screen.getText() + "0.");
                    } else {
                        screen.setText(screen.getText() + ".");
                    }
                    decimal = true;
                }
            }
        });

        butt_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ac_click){
                    input2 = 0.0;
                    butt_c.setText(R.string.ec);
                    ac_click = !ac_click;
                }else {
                    input1 = 0.0;
                    input2 = 0.0;
                    butt_c.setText(R.string.c);
                    ac_click = !ac_click;
                }
                decimal = !decimal;
                screen.setText("");
            }
        });

        butt_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decimal = !decimal;
                screen.setText("");
                input1 = 0.0;
                input2 = 0.0;
            }
        });

        butt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screen.getText().length() != 0) {

                        input1 = Double.parseDouble(screen.getText() + "");
                        Addition = true;
                        decimal = false;
                        screen.setText(null);
                    }
                }

        });

        butt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screen.getText().length() != 0) {
                    input1 = Double.parseDouble((String) screen.getText());
                    Subtract = true;
                    decimal = false;
                    screen.setText(null);
                }
            }
        });

        butt_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screen.getText().length() != 0) {
                    input1 = Double.parseDouble((String)screen.getText());
                    Multiplication = true;
                    decimal = false;
                    screen.setText(null);
                }
            }
        });

        butt_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screen.getText().length() != 0) {
                    input1 = Double.parseDouble(screen.getText() + "");
                    Division = true;
                    decimal = false;
                    screen.setText(null);
                }
            }
        });

        butt_equ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equal();
            }
        });

        butt_opp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screen.getText().length() != 0 && Double.parseDouble((String)screen.getText()) != 0) {
                    input1 = Double.parseDouble(screen.getText() + "");
                    input1 = -input1;
                    screen.setText(parseOutput(input1));
                }
            }
        });
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


    private void equal() {
        if (Addition || Subtract || Multiplication || Division) {
            input2 = Double.parseDouble(screen.getText() + "");
        }

        System.out.println(input1);
        System.out.println(input2);
            BigDecimal bdi1 = new BigDecimal(input1);
            BigDecimal bdi2 = new BigDecimal(input2);

        if (Addition) {
            screen.setText(bdi1.add(bdi2) + "");
            Addition=false;
            Subtract=false;
            Multiplication=false;
            Division = false;
        }

        if (Subtract) {
            screen.setText(bdi1.subtract(bdi2) + "");
            Addition=false;
            Subtract=false;
            Multiplication=false;
            Division = false;
        }

        if (Multiplication) {
            BigDecimal result = bdi1.multiply(bdi2, MathContext.DECIMAL32);
            screen.setText(parseOutput(result.doubleValue()));
            Addition=false;
            Subtract=false;
            Multiplication=false;
            Division = false;
        }

        if (Division) {
            if(input2 == 0.0) {
                screen.setText("");
            } else {
                BigDecimal result = bdi1.divide(bdi2, MathContext.DECIMAL32);
                screen.setText(parseOutput(result.doubleValue()));
            }
            Addition=false;
            Subtract=false;
            Multiplication=false;
            Division = false;
        }
    }
}