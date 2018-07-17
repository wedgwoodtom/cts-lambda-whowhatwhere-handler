package com.cts.questions;

import com.cts.client.QuestionClient;
import com.cts.data.Question;
import org.junit.Test;

import java.util.List;

/**
 * Run this to import the resources/questions.csv file.  Note that ALL existing questions are deleted and replaced.
 *
 */
public class QuestionImporterIntegrationTest
{
    @Test
    public void importQuestionsFromFile() throws Exception
    {
        QuestionClient questionClient = new QuestionClient().init();
        List<Question> questions = new QuestionFileParser().parseQuestions("questions.csv");
        System.out.println("Deleting Existing Questions...");
        questionClient.deleteAll();
        System.out.println("Inserting "+questions.size()+" Questions...");
        for (Question question : questions)
        {
            questionClient.save(question);
            System.out.println("Inserted "+ question);
        }
        System.out.println("Import Complete!");
    }
}



