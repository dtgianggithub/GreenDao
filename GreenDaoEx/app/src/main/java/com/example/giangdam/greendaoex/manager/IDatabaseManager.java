package com.example.giangdam.greendaoex.manager;

import com.example.giangdam.greendaoex.dao.DBPhoneNumber;
import com.example.giangdam.greendaoex.dao.DBUser;
import com.example.giangdam.greendaoex.dao.DBUserDetails;

import java.util.List;
import java.util.Set;

/**
 * Created by Giang.Dam on 8/18/2015.
 * Interface that provides methods for managing database inside Application
 */
public interface IDatabaseManager {

    /**
     *Closing availble connections
     */
    public void closeDbConnections();


    /**
     * Delete all tables and content from our database
     */
    public void dropDatabase();

    /**
     * Insert the user into database
     */
    public DBUser insertUser(DBUser user);


    /**
     * Get all users from databse
     */
    public List<DBUser> listUsers();

    /**
     * Update User from database
     */
    public void updateUser(DBUser user);


    /**
     * Delete all users with certain email from database
     */
    public void deleteUserByEmail(String email);

    /**
     * Delete users with certain id from database
     */
    public boolean deleteUserById(Long userId);

    /**
     * Get user by userId
     */
    public DBUser getUserById(Long userId);

    /**
     * Delete all users from database
     */
    public void deleteUsers();


    /**
     * Insert or update userDetails object into database
     */
    public DBUserDetails insertOrUpdateUserDetails(DBUserDetails userDetails);


    /**
     * Delete a user by name and gender
     */
    public void deleteUserByFirstNameAndGender(String firstName, String gender);

    /**
     * Insert or update a phoneNumber object into the DB
     *
     * @param phoneNumber to be inserted/updated
     */
    public void insertOrUpdatePhoneNumber(DBPhoneNumber phoneNumber);

    /**
     * Insert or update a list of phoneNumbers into the DB
     *
     * @param phoneNumbers - list of objects
     */
    public void bulkInsertPhoneNumbers(Set<DBPhoneNumber> phoneNumbers);

}
