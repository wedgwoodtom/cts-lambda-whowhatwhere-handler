package com.cts;

import com.cts.client.QuestionClient;
import com.cts.data.Question;
import com.cts.data.Term;
import com.cts.data.TermType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuestionClientIntegrationTest
{
    private static QuestionClient questionClient;
    private static String lastQuestionId;

    @BeforeClass
    public static void setup()
    {
        questionClient = new QuestionClient().init();
    }

    @Test
    public void test01Save()
    {
        Question question = new Question();

        question.setWho(Term.from(TermType.THING, "Sputnik 1"));
        question.setWhat(Term.from(TermType.THING, "Satellite"));
        question.setWhere(Term.from(TermType.PLACE, "Soviet Union"));

        questionClient.save(question);

        lastQuestionId = question.getId();
    }

    @Test
    public void test02Read()
    {
        Question fromDb = questionClient.load(lastQuestionId);
        Assert.assertNotNull(fromDb);
    }

    @Test
    public void test03Delete()
    {
        questionClient.delete(lastQuestionId);
        Question fromDb = questionClient.load(lastQuestionId);
        Assert.assertNull(fromDb);
    }

    @Test
    public void createTestData()
    {
        Question question = new Question();

        question.setWho(Term.from(TermType.THING, "Sputnik 3"));
        question.setWhat(Term.from(TermType.THING, "Satellite 3"));
        question.setWhere(Term.from(TermType.PLACE, "Soviet Union 3"));

        questionClient.save(question);
    }

    @Test
    public void testGetRandom()
    {
        for (int i=0; i<3; i++)
        {
            System.out.println(questionClient.findRandomQuestion());
        }
    }


//    @Test
    public void test04DeleteAll()
    {
        questionClient.deleteAll();
        Assert.assertTrue(questionClient.getAll().isEmpty());

    }

}
