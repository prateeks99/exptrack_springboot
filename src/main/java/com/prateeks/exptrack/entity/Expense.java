package com.prateeks.exptrack.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "ExpenseTrackerTable")
public class Expense {

    @DynamoDBHashKey(attributeName = "expenseId")
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private BigDecimal amount;

    @DynamoDBAttribute
    private String category;

    @DynamoDBAutoGeneratedTimestamp
    private Date time;
}
