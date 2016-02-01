package com.zacck.androidbraintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button bStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStart = (Button)findViewById(R.id.btStartButton);

        TextView sumTextView = (TextView)findViewById(R.id.tvSumView);

        //logic
        //generate some random integers'
        Random mRandom = new Random();

        int a = mRandom.nextInt(21);
        int b = mRandom.nextInt(21);

        //populate the TextView with our sum 
        sumTextView.setText(Integer.toString(a)+ " + "+Integer.toString(b));



    }

    public void start(View view)
    {
        bStart.setVisibility(View.INVISIBLE);

    }
}
