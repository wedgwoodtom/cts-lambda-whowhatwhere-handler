package com.cts;

import com.cts.data.GameQuestionData;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class WhoWhatWhereGameDataHandlerIntegrationTest
{
    @Test
    public void testGetQuestions()
    {
        WhoWhatWhereGameDataHandler whoWhatWhereGameDataHandler = new WhoWhatWhereGameDataHandler();
        GameQuestionData data = whoWhatWhereGameDataHandler.getQuestionData(GetQuestionsRequest.from(0, 10));
        Assert.assertNotNull(data);
        Assert.assertEquals(10, data.getQuestions().size());
        Gson gson = new Gson();
        System.out.println(gson.toJson(data));
    }
//
//    @Test
//    public void testFindCurrentStudents()
//    {
//        WhoWhatWhereGameDataHandler whoWhatWhereGameDataHandler = new WhoWhatWhereGameDataHandler();
//        GetGameDataRequest request = new GetGameDataRequest();
//        request.setCurrentTime(new Date());
//        request.setTeacherId("ww.tom@gmail.com");
//        List<String> students = whoWhatWhereGameDataHandler.findCurrentStudents(request).getStudents();
//        Assert.assertNotNull(students);
//        Assert.assertTrue(students.size()>0);
//    }
//
//    @Test
//    public void testFindCurrentStudentsWhenNoStudents()
//    {
//        WhoWhatWhereGameDataHandler whoWhatWhereGameDataHandler = new WhoWhatWhereGameDataHandler();
//        GetGameDataRequest request = new GetGameDataRequest();
//        request.setCurrentTime(new Date());
//        request.setTeacherId("BAD_ID");
//        List<String> students = whoWhatWhereGameDataHandler.findCurrentStudents(request).getStudents();
//        Assert.assertTrue(students.isEmpty());
//    }
}
