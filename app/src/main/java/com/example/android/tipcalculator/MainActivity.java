package com.example.android.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText total_bill;
    private EditText total_dining;
    private SeekBar tseekBar;
    private TextView percLabel;
    private CheckBox split;
    private TextView tiplabel;
    private double total;
    private int diners;
    private double tip;
    private TextView totaltip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        percLabel = findViewById(R.id.percLabel);
        tseekBar = findViewById(R.id.perc);
        total_bill = findViewById(R.id.total_bill);
        total_dining = findViewById(R.id.dinerNum);
        split = findViewById(R.id.splitbox);
        tiplabel = findViewById(R.id.tipamount);
        split = findViewById(R.id.splitbox);
        totaltip=findViewById(R.id.totaltip);


        total = 0.0;
        diners = 0;
        total_bill.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String t = total_bill.getText().toString();

                            total = Double.parseDouble(t);
                            return false;



                    }
                }
        );
        total_dining.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String d = total_dining.getText().toString();
                        diners = Integer.parseInt(d);
                        return false;
                    }
                }

        );
        tseekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        percLabel.setText(i + "" + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

    }

    public void calculate(View v) {
        double t;
        double total_tip;
        double progress = tseekBar.getProgress();
        t=total;
        if (split.isChecked()) {
            tip = t * (progress / 100);
            total_tip= (total + tip)/diners;
            tiplabel.setText("Tip: $" +String.format("%.2f",tip));
            totaltip.setText("Total: $"+String.format("%.2f",total_tip)+" per person");
        }else{
            tip=t*(progress/100);
            total_tip= total+tip;
            tiplabel.setText("Tip: $"+String.format("%.2f",tip));
            totaltip.setText("Total is: $"+String.format("%.2f",total_tip));
        }


    }
}