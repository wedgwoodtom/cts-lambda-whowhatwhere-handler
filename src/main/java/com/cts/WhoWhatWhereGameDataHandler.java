package com.cts;

import com.cts.client.QuestionClient;
import com.cts.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This is the Lambda - com.cts.WhoWhatWhereGameDataHandler::getQuestionData
public class WhoWhatWhereGameDataHandler
{
    private QuestionClient questionClient = new QuestionClient().init();

    public GameQuestionData getQuestionData(GetQuestionsRequest questionsRequest)
    {
        GameQuestionData data = new GameQuestionData();
        List<Question> randomQuestions = getQuestions(questionsRequest);
        data.setQuestions(randomQuestions);
        data.setAnswersByType(getTermTypeMap(randomQuestions));

        return data;
    }

    List<Question> getQuestions(GetQuestionsRequest questionsRequest)
    {
        List<Question> questions = new ArrayList<>();

        int numFinds = 1;
        int numQuestions = questionsRequest.getNumberQuestions();
        Question q = questionClient.findRandomQuestion();
        while (questions.size() < numQuestions)
        {
            if (!questions.contains(q))
            {
                questions.add(q);
            }
            if (questions.size() != numQuestions)
            {
                q = questionClient.findRandomQuestion();
                numFinds++;
                if (numFinds > 100) // sanity check to prevent infinite loop
                {
                    break;
                }
            }
        }
        return questions;
    }

    Map<TermType, List<String>> getTermTypeMap(List<Question> questions)
    {
        Map<TermType, List<String>> termTypeMap = new HashMap<TermType, List<String>>();
        // TODO: Perhaps get these from the db as well eventually
        for (Question question : questions)
        {
            // TODO: debug why this is null!
            if (question != null)
            {
                addTermValue(question.getWho(), termTypeMap);
                addTermValue(question.getWhat(), termTypeMap);
                addTermValue(question.getWhere(), termTypeMap);
            }
        }
        return termTypeMap;
    }

    void addTermValue(Term term, Map<TermType, List<String>> termTypeMap)
    {
        TermType key = term.getType();
        String value = term.getValue();

        termTypeMap.putIfAbsent(key, new ArrayList<String>());
        if (!termTypeMap.get(key).contains(value))
        {
            termTypeMap.get(key).add(value);
        }
    }

}
