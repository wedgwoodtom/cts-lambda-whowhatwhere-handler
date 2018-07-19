package com.cts.client;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cts.data.Question;

import java.util.*;
import java.util.stream.Collectors;

public class QuestionClient
{
    private DynamoDBMapper mapper;
    private Regions REGION = Regions.US_WEST_2;

    public QuestionClient init()
    {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        mapper = new DynamoDBMapper(client);

        return this;
    }

    public void save(Question question)
    {
        mapper.save(question);
    }

    public Question load(String Id)
    {
        return mapper.load(Question.class, Id);
    }

    public void delete(String Id)
    {
        Question toDelete = new Question();
        toDelete.setId(Id);
        mapper.delete(toDelete);
    }

    public void deleteAll()
    {
        getAll().forEach(question -> mapper.delete(question));
    }

    public List<Question> getAll()
    {
        List<Question> questions = new ArrayList<>();
        questions.addAll(mapper.scan(Question.class, new DynamoDBScanExpression())
            .stream().collect(Collectors.toList())
        );
        return questions;
    }

    public Question findRandomQuestion()
    {
        // This crazy idea works: https://stackoverflow.com/questions/10666364/aws-dynamodb-pick-a-record-item-randomly?lq=1
        Map<String, AttributeValue> bogusKey = new HashMap<String, AttributeValue>();
        bogusKey.put("Id", new AttributeValue().withS(UUID.randomUUID().toString()));

        Optional<Question> question = mapper.scan(Question.class,
            new DynamoDBScanExpression()
                .withExclusiveStartKey(bogusKey)
                .withLimit(1)
        ).stream().findFirst();

        return question.isPresent() ? question.get() : null;
    }

//    static void deleteExampleTable() {
//
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        String tableName = "Question";
//
//        Table table = dynamoDB.getTable(tableName);
//        try {
//            System.out.println("Issuing DeleteTable request for " + tableName);
//            table.delete();
//
//            System.out.println("Waiting for " + tableName + " to be deleted...this may take a while...");
//
//            table.waitForDelete();
//        }
//        catch (Exception e) {
//            System.err.println("DeleteTable request failed for " + tableName);
//            System.err.println(e.getMessage());
//        }
//    }

}
