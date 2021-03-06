package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.toRadians;


public class ScienceCalc extends AppCompatActivity {

    private static final int[] buttons_num_ids = {
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

    private static final int[] buttons_num_val = {
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

    boolean Addition, Subtract, Multiplication, Division, Sqrt, Square, Power, Sin, Cos, Tan, Log, Ln, ac_click, decimal;

    String input;

    Button butt_dot, butt_c, butt_ac, butt_add, butt_sub, butt_mul, butt_div, butt_equ, butt_opp;
    Button butt_sqrt, butt_square, butt_pow, butt_sin, butt_cos, butt_tan, butt_log, butt_ln;

    TextView screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_calc);

        screen = (TextView) findViewById(R.id.calc_value);

        butt_dot = (Button) findViewById(R.id.dot);
        butt_c = (Button) findViewById(R.id.c);
        butt_ac = (Button) findViewById(R.id.ac);
        butt_add = (Button) findViewById(R.id.add);
        butt_sub = (Button) findViewById(R.id.sub);
        butt_div = (Button) findViewById(R.id.div);
        butt_mul = (Button) findViewById(R.id.multiply);
        butt_equ = (Button) findViewById(R.id.calc);
        butt_opp = (Button) findViewById(R.id.opposite);
        butt_sqrt = (Button) findViewById(R.id.sqrt);
        butt_square = (Button) findViewById(R.id._x_2);
        butt_pow = (Button) findViewById(R.id._x_y);
        butt_sin = (Button) findViewById(R.id.sin);
        butt_cos = (Button) findViewById(R.id.cos);
        butt_tan = (Button) findViewById(R.id.tan);
        butt_log = (Button) findViewById(R.id.log);
        butt_ln = (Button) findViewById(R.id.ln);

        CharSequence butt_num;

        ac_click = false;

        for (int ids = 0; ids < buttons_num_ids.length; ids++) {
            final Button button = (Button) findViewById(buttons_num_ids[ids]);
            butt_num = getText(buttons_num_val[ids]);
            final String finalButt_num = String.valueOf(butt_num);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    screen.setText(parseOutput(Double.parseDouble((String) screen.getText() + finalButt_num)));
                }
            });
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
                if (screen.getText().length() != 0 && Double.parseDouble((String) screen.getText()) != 0) {
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

        butt_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqrt();
                show();
                equal();
            }
        });

        butt_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Square();
                show();
                equal();
            }
        });

        butt_pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Power();
                show();
            }
        });

        butt_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sin();
                show();
                equal();
            }
        });

        butt_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cos();
                show();
                equal();
            }
        });

        butt_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tan();
                show();
                equal();
            }
        });

        butt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log();
                show();
                equal();
            }
        });

        butt_ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ln();
                show();
                equal();
            }
        });

    }

    private void show() {
        if (screen.getText().length() != 0) {
            switcher = 1;
            input1 = Double.parseDouble((String) screen.getText());
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
        double val;
        System.out.println(input1);
        if (screen.getText() != "") {
            if (Addition || Subtract || Multiplication || Division || Power) {
                input2 = Double.parseDouble((String) screen.getText());
            }
            bdi1 = new BigDecimal(input1);
            bdi2 = new BigDecimal(input2);

            if (Addition) {
                BigDecimal result = bdi1.add(bdi2, MathContext.DECIMAL32);
                if (isResultInfinity(result.doubleValue())) {
                    screen.setText("");
                    input1 = input2 = 0;
                } else {
                    screen.setText(parseOutput(result.doubleValue()));
                }
                reset();
            }

            if (Subtract) {
                BigDecimal result = bdi1.subtract(bdi2, MathContext.DECIMAL32);
                if (isResultInfinity(result.doubleValue())) {
                    screen.setText("");
                    input1 = input2 = 0;
                } else {
                    screen.setText(parseOutput(result.doubleValue()));
                }
                reset();
            }

            if (Multiplication) {
                BigDecimal result = bdi1.multiply(bdi2, MathContext.DECIMAL32);
                if (isResultInfinity(result.doubleValue())) {
                    screen.setText("");
                    input1 = input2 = 0;
                } else if (input2 == 0) {
                    showException("You cannot multiply by zero!");
                    screen.setText("");
                    input1 = input2 = 0;
                } else {
                    screen.setText(parseOutput(result.doubleValue()));
                }
                reset();
            }

            if (Division) {
                if (input2 == 0.0) {
                    showException("You cannot divide by zero!");
                    screen.setText("");
                    input1 = input2 = 0;
                } else {
                    BigDecimal result = bdi1.divide(bdi2, MathContext.DECIMAL32);
                    screen.setText(parseOutput(result.doubleValue()));
                }
                reset();
            }

            if (Power) {
                val = pow(input1, input2);
                if (isResultInfinity(val)) {
                    input1 = input2 = 0;
                    screen.setText("");
                } else {
                    screen.setText(parseOutput(val));
                }
                reset();
            }

            butt_add.setBackground(getResources().getDrawable(R.drawable.custom_button));
            butt_sub.setBackground(getResources().getDrawable(R.drawable.custom_button));
            butt_div.setBackground(getResources().getDrawable(R.drawable.custom_button));
            butt_mul.setBackground(getResources().getDrawable(R.drawable.custom_button));
        }

        bdi1 = new BigDecimal(input1);

        if (Sqrt) {
            if (input1 > 0) {
                val = sqrt(input1);
                screen.setText(String.format("%s%s", screen.getText(), parseOutput(val)));
            } else {
                showException("Inadequate value!");
                screen.setText("");
                input1 = 0;
            }
            reset();
        }

        if (Square) {
            val = pow(input1, 2);
            String var = parseOutput(val);
            if (var.length() > 9) {
                showException("The result is off the scale");
                input1 = input2 = 0;
            } else {
                screen.setText(String.format("%s%s", screen.getText(), parseOutput(val)));
            }
            reset();
        }

        if (Sin) {
            while (input1 < 0) {
                input1 += 360;
            }
            double v1 = input1 % 360;
            val = sin(toRadians(v1));
            screen.setText(String.format("%s%s", screen.getText(), parseOutput(val)));
            reset();
        }

        if (Cos) {
            while (input1 < 0) {
                input1 += 360;
            }
            double v1 = input1 % 360;
            val = cos(toRadians(v1));
            screen.setText(String.format("%s%s", screen.getText(), parseOutput(val)));
            reset();
        }

        if (Tan) {
            while (input1 < 0) {
                input1 += 360;
            }
            double v1 = input1 % 360;
            val = tan(toRadians(v1));
            screen.setText(String.format("%s%s", screen.getText(), parseOutput(val)));
            reset();
        }

        if (Log) {
            if (input1 > 0) {
                val = log10(input1);
                screen.setText(String.format("%s%s", screen.getText(), parseOutput(val)));
            } else {
                showException("You should use a non-negative value!");
                input1 = 0;
                screen.setText("");
            }
            reset();
        }

        if (Ln) {
            if (input1 > 0) {
                val = log(input1);
                screen.setText(String.format("%s%s", screen.getText(), parseOutput(val)));
            } else {
                showException("You should use a non-negative value!");
                input1 = 0;
                screen.setText("");
            }
            reset();
        }
    }

    private void reset() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Sin = false;
        Power = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Add() {
        Addition = true;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Sub() {
        Addition = false;
        Subtract = true;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Mul() {
        Addition = false;
        Subtract = false;
        Multiplication = true;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Div() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = true;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Sqrt() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = true;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Square() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = true;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Power() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = true;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Sin() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = true;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Cos() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = true;
        Tan = false;
        Log = false;
        Ln = false;
    }

    private void Tan() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = true;
        Log = false;
        Ln = false;
    }

    private void Log() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = true;
        Ln = false;
    }

    private void Ln() {
        Addition = false;
        Subtract = false;
        Multiplication = false;
        Division = false;
        Sqrt = false;
        Square = false;
        Power = false;
        Sin = false;
        Cos = false;
        Tan = false;
        Log = false;
        Ln = true;
    }

    private boolean isResultInfinity(Double number) {
        if (number.toString().contains("Infinity")) {
            showException("The result is off the scale");
            return true;
        }
        return false;
    }

    private void showException(String exceptionValue) {
        Toast.makeText(ScienceCalc.this, exceptionValue, Toast.LENGTH_SHORT).show();
    }

}