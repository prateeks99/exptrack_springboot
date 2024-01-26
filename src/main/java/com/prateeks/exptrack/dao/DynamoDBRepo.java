package com.prateeks.exptrack.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.*;
import com.prateeks.exptrack.entity.Expense;
import com.prateeks.exptrack.entity.User;
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

    public User getUser(String userId) {
        return dynamoDBMapper.load(User.class, userId);
    }

    public User saveUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public String deleteUser(String userId) {
        User user = dynamoDBMapper.load(User.class, userId);
        dynamoDBMapper.delete(user);
        return "User Deleted!";
    }

    public Expense getExpenseById(String expenseId, String userId) {
        return dynamoDBMapper.load(Expense.class, userId, expenseId);
    }

    public List<Expense> getAll(String userId) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("userId", new Condition()
                .withComparisonOperator("CONTAINS")
                .withAttributeValueList(new AttributeValue().withS(userId)));

        return dynamoDBMapper.scan(Expense.class, scanExpression);
    }

    public String delete(String userId, String expenseId) {
        Expense exp = dynamoDBMapper.load(Expense.class, userId, expenseId);
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
