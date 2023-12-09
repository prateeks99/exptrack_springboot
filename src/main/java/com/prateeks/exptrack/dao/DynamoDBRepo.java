package com.prateeks.exptrack.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.*;
import com.prateeks.exptrack.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DynamoDBRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Expense save(Expense expense) {
        dynamoDBMapper.save(expense);
        return expense;
    }

    public List<Expense> getByCategory(String category) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("category", new Condition()
                .withComparisonOperator("CONTAINS")
                .withAttributeValueList(new AttributeValue().withS(category)));

        return dynamoDBMapper.scan(Expense.class, scanExpression);
    }

    public Expense getExpenseById(String expenseId) {
        return dynamoDBMapper.load(Expense.class, expenseId);
    }

    public List<Expense> getAll() {
        return dynamoDBMapper.scan(Expense.class, new DynamoDBScanExpression());
    }

    public String delete(String expenseId) {
        Expense exp = dynamoDBMapper.load(Expense.class, expenseId);
        dynamoDBMapper.delete(exp);
        return "Expense Deleted!";
    }

    public String update(String expenseId, Expense expense) {
        dynamoDBMapper.save(expense,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("expenseId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(expenseId)
                                )));
        return expenseId;
    }

}
