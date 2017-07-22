package com.example.kenandrews.geoquiz;

/**
 * Created by kenandrews on 7/13/17.
 * From Chapter 2 of Android Programming : The Big Nerd Ranch
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mAnswered;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mAnswered = false;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public void setAnswered() { mAnswered = true;}

    public boolean getAnswered() { return mAnswered;}
}
