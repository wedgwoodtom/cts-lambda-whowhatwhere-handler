package com.cts;

import java.util.Date;

public class GetQuestionsRequest
{
    private int difficultLevel;
    private int numberQuestions;

    public int getDifficultLevel()
    {
        return difficultLevel;
    }

    public void setDifficultLevel(int difficultLevel)
    {
        this.difficultLevel = difficultLevel;
    }

    public int getNumberQuestions()
    {
        return numberQuestions;
    }

    public void setNumberQuestions(int numberQuestions)
    {
        this.numberQuestions = numberQuestions;
    }

    public static GetQuestionsRequest from(int difficultLevel, int numberQuestions)
    {
        GetQuestionsRequest request = new GetQuestionsRequest();
        request.setDifficultLevel(difficultLevel);
        request.setNumberQuestions(numberQuestions);
        return request;
    }
}
