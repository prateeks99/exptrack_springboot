package com.prateeks.exptrack.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "users_exptrack")
public class User {

    @DynamoDBHashKey(attributeName = "userId")
    private String username;

    @DynamoDBAttribute
    private String password;

    @DynamoDBAttribute
    private String first_name;

    @DynamoDBAttribute
    private String last_name;

    @DynamoDBAttribute
    private String email_id;

}
