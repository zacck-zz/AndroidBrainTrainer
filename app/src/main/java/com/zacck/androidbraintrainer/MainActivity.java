package com.zacck.androidbraintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button bStart;
    ArrayList<Integer> answersArray = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score =0;
    TextView mResTextView;
    TextView scoreText;
    int numberOfQuestions;
    TextView sumTextView;
    Button bt0;
    Button bt1;
    Button bt2;
    Button bt3;
    TextView mTimerTextView;
    Button btPlayAgain;
    RelativeLayout mGameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStart = (Button)findViewById(R.id.btStartButton);
        mTimerTextView = (TextView)findViewById(R.id.timerTextView);
        mGameLayout = (RelativeLayout)findViewById(R.id.gameLayout);

        sumTextView = (TextView)findViewById(R.id.tvSumView);
        bt0 = (Button)findViewById(R.id.btZero);
        bt1 = (Button)findViewById(R.id.btOne);
        bt2 = (Button)findViewById(R.id.btTwo);
        bt3 = (Button)findViewById(R.id.btThree);
        mResTextView = (TextView)findViewById(R.id.resultTextView);
        scoreText = (TextView)findViewById(R.id.pointsTextView);
        btPlayAgain = (Button)findViewById(R.id.btPlayAgain);


        playAgain(btPlayAgain);







    }
    public void playAgain(View view)
    {

        score = 0;
        numberOfQuestions = 0;
        mTimerTextView.setText("30s");
        scoreText.setText("0/0");
        mResTextView.setText("");
        btPlayAgain.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerTextView.setText(String.valueOf(millisUntilFinished/1000) +"s");
            }

            @Override
            public void onFinish() {

                btPlayAgain.setVisibility(View.VISIBLE);

                mTimerTextView.setText("0s");
                mResTextView.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

            }
        }.start();
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            mResTextView.setText("Correct!");
        }
        else
        {
            mResTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreText.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestion();



    }
    public void generateQuestion()
    {
        //logic
        //generate some random integers'
        Random mRandom = new Random();

        int a = mRandom.nextInt(21);
        int b = mRandom.nextInt(21);

        //populate the TextView with our sum
        sumTextView.setText(Integer.toString(a)+ " + "+Integer.toString(b));

        //generate 3 incorrect numbers plus location of correct
        locationOfCorrectAnswer = mRandom.nextInt(4);

        answersArray.clear();

        //incorrect answer handling
        int IncorrectAnswer;
        for(int i=0; i<4; i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answersArray.add(a+b);
            }
            else
            {
                IncorrectAnswer = mRandom.nextInt(41);

                while(IncorrectAnswer == a+b)
                {
                    IncorrectAnswer = mRandom.nextInt(41);
                }
                answersArray.add(IncorrectAnswer);
            }
        }

        //update the buttons with the values
        bt0.setText(Integer.toString(answersArray.get(0)));
        bt1.setText(Integer.toString(answersArray.get(1)));
        bt2.setText(Integer.toString(answersArray.get(2)));
        bt3.setText(Integer.toString(answersArray.get(3)));

    }

    public void start(View view)
    {
        bStart.setVisibility(View.INVISIBLE);
        mGameLayout.setVisibility(View.VISIBLE);
        playAgain(btPlayAgain);


    }
}
