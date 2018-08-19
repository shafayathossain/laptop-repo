package com.example.dcc.bestlaptop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    TickSeekBar tickSeekBar;
    TextView budgetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RanklistActivity.class);
                intent.putExtra("budget", tickSeekBar.getProgress());
                startActivity(intent);
            }
        });

        tickSeekBar = findViewById(R.id.budgetSeekBar);
        budgetTextView =  findViewById(R.id.budgetTextView);
        tickSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                budgetTextView.setText(Integer.toString(seekParams.progress));
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });
    }
}
