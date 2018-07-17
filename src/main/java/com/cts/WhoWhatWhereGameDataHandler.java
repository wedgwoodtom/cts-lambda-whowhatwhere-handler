package com.cts;

import com.cts.client.QuestionClient;
import com.cts.data.*;

// This is the Lambda - com.cts.WhoWhatWhereGameDataHandler::getQuestionData
public class WhoWhatWhereGameDataHandler
{
    private QuestionClient questionClient = new QuestionClient().init();

    public GameQuestionData GetQuestionData(int difficultLevel, int numberQuestions)
    {
        return new GameQuestionData();
    }

}
