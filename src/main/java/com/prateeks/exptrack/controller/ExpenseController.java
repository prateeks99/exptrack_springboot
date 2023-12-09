package com.prateeks.exptrack.controller;

import com.prateeks.exptrack.entity.Expense;
import com.prateeks.exptrack.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpense();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpenseByCategory(@PathVariable String category) {
        List<Expense> expenses = expenseService.getExpenseByCategory(category);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpense(@PathVariable String expenseId) {
        Expense expense = expenseService.getExpenseById(expenseId);
        if (expense != null) {
            return new ResponseEntity<>(expense,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense addedExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(addedExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable String expenseId, @RequestBody Expense expense) {
        logger.info(expenseId);
        logger.info(expense.getName());
        return new ResponseEntity<>(expenseService.updateExpense(expenseId, expense), HttpStatus.OK);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable String expenseId) {
        String resp = expenseService.deleteExpense(expenseId);
        if (resp.equals("Expense Deleted!")) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
