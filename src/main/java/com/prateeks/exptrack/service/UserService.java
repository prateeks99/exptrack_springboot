package com.prateeks.exptrack.service;

import com.prateeks.exptrack.dao.DynamoDBRepo;
import com.prateeks.exptrack.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    DynamoDBRepo dynamoDBRepo;

    public User getUser(String username) {
        return dynamoDBRepo.getUser(username);
    }

    public User createUser(User user) {
        return dynamoDBRepo.saveUser(user);
    }

    public String deleteUser(String username) {
        return dynamoDBRepo.deleteUser(username);
    }

}
