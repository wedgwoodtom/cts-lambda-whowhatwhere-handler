package com.cts.data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameQuestionData
{
    private List<Question> questions = Collections.emptyList();
    private Map<TermType, List<String>> answersByType = Collections.EMPTY_MAP;

    public List<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }

    public Map<TermType, List<String>> getAnswersByType()
    {
        return answersByType;
    }

    public void setAnswersByType(Map<TermType, List<String>> answersByType)
    {
        this.answersByType = answersByType;
    }
}
