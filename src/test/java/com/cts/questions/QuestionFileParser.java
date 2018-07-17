package com.cts.questions;

import com.cts.data.Question;
import com.cts.data.Term;
import com.cts.data.TermType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse Questions from a file
 */
public class QuestionFileParser
{
    public List<Question> parseQuestions(String resourceFileName)
    {
        List<Question> questions = new ArrayList<>();
        int lineNo = 2;   // account for header and 1-based counting
        try
        {
            byte[] csvBytes =
                    Files.readAllBytes(
                            Paths.get(getClass().getClassLoader().getResource(resourceFileName).toURI()));

            Reader reader = new InputStreamReader(new ByteArrayInputStream(csvBytes));
            CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader().withIgnoreEmptyLines());
            try
            {
                for (CSVRecord record : parser.getRecords())
                {
                    lineNo++;
                    Question question = fromRecord(record);
                    if (question != null)
                    {
                        questions.add(question);
                        System.out.println("Parsed: " + question);
                    }
                    {
                        System.out.println("Skipping blank line: "+lineNo);
                    }
                }
            } finally
            {
                parser.close();
                reader.close();
            }
        } catch (Exception error)
        {
            throw new RuntimeException("Error in processing line:" + lineNo + " of file:" + resourceFileName, error);
        }

        return questions;
    }

    Question fromRecord(CSVRecord row)
    {
        // skip if an 'empty' record
        String who = row.get("Who");
        String what = row.get("What");
        String where = row.get("Where");

        if (who.isEmpty() && what.isEmpty() && where.isEmpty())
        {
            return null;
        }

        Question question = new Question();
        question.setWho(Term.from(TermType.valueOf(row.get("WhoType")), who));
        question.setWhat(Term.from(TermType.valueOf(row.get("WhatType")), what));
        question.setWhere(Term.from(TermType.valueOf(row.get("WhereType")), where));
        return question;
    }

}
