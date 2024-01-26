package com.prateeks.exptrack.service;

import com.prateeks.exptrack.dao.DynamoDBRepo;
import com.prateeks.exptrack.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    DynamoDBRepo dynamoDBRepo;

    public List<Expense> getAllExpense(String userId) {
        return dynamoDBRepo.getAll(userId);
    }

    public List<Expense> getExpenseByCategory(String category){
        return dynamoDBRepo.getByCategory(category);
    }

    public Expense getExpenseById(String expenseId, String userId) {
        return dynamoDBRepo.getExpenseById(expenseId, userId);
    }

    public Expense addExpense(Expense expense) {
        return dynamoDBRepo.save(expense);
    }

    public String updateExpense(String expenseId, Expense expense) {
        return dynamoDBRepo.update(expenseId, expense);
    }

    public String deleteExpense(String userId, String expenseId) {
        return dynamoDBRepo.delete(userId, expenseId);
    }

}
