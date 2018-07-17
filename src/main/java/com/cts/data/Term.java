package com.cts.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

@DynamoDBDocument
public class Term
{
    private String value;
    private TermType type;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    public TermType getType()
    {
        return type;
    }

    public void setType(TermType type)
    {
        this.type = type;
    }

    public static Term from(TermType termType, String value)
    {
        Term term = new Term();
        term.setType(termType);
        term.setValue(value);
        return term;
    }

    @Override
    public String toString()
    {
        return "Term{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
