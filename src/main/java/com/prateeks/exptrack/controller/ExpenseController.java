package com.prateeks.exptrack.controller;

import com.prateeks.exptrack.entity.Expense;
import com.prateeks.exptrack.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getAllExpenses(@PathVariable String userId) {
        List<Expense> expenses = expenseService.getAllExpense(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpenseByCategory(@PathVariable String category) {
        List<Expense> expenses = expenseService.getExpenseByCategory(category);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{expenseId}")
    public ResponseEntity<Expense> getExpense(@PathVariable String expenseId, @PathVariable String userId) {
        Expense expense = expenseService.getExpenseById(expenseId, userId);
        if (expense != null) {
            return new ResponseEntity<>(expense,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense addedExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(addedExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable String expenseId, @RequestBody Expense expense) {
        return new ResponseEntity<>(expenseService.updateExpense(expenseId, expense), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{expenseId}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable String userId, @PathVariable String expenseId) {
        String resp = expenseService.deleteExpense(userId, expenseId);
        if (resp.equals("Expense Deleted!")) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
