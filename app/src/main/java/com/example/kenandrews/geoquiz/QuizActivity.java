package com.example.kenandrews.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String SCORE_KEY = "KeyScore";
    private static final String QUESTION_INDEX_KEY = "KeyQuestionIndex";
    // private static final String QUESTION_BANK_KEY = "KeyQuestionBank";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private TextView mScoreView;
    private int mScore = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate started");

        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(QUESTION_INDEX_KEY);
            mScore = savedInstanceState.getInt(SCORE_KEY);
        }

        mScoreView = (TextView) findViewById(R.id.score_text_view);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause started");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(SCORE_KEY, mScore);
        savedInstanceState.putInt(QUESTION_INDEX_KEY, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy started");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        mScoreView.setText(String.valueOf(mScore));
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = R.string.incorrect_toast;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            mScore++;
        }

        mQuestionBank[mCurrentIndex].setAnswered();

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
}
