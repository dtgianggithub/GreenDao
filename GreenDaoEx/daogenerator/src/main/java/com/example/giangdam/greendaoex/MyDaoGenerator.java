package com.example.giangdam.greendaoex;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {


    //get base of non-absolut path
    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.example.giangdam.greendaoex.dao");
        //add table
        addTables(schema);
        //generate
        //"C:/Users/Giang.Dam/AndroidStudioProjects/GreenDaoEx/app/src/main/java"
        new DaoGenerator().generateAll(schema,OUT_DIR);
    }

    private static void addTables(Schema schema) {
        //Etities ---- table
        Entity user = addUser(schema);
        Entity userDetails = addUserDetails(schema);
        Entity phoneNumber = addPhoneNumber(schema);


          /* properties */
        Property userIdForUserDetails = userDetails.addLongProperty("userId").notNull().getProperty();
        Property userDetailsIdForUser = user.addLongProperty("detailsId").notNull().getProperty();
        Property userDetailsIdForPhoneNumber = phoneNumber.addLongProperty("detailsId").notNull().getProperty();

        /* relationships between entities */
        userDetails.addToOne(user, userIdForUserDetails, "user");    // one-to-one (user.getDetails)
        user.addToOne(userDetails, userDetailsIdForUser, "details"); // one-to-one (user.getUser)

        ToMany userDetailsToPhoneNumbers = userDetails.addToMany(phoneNumber, userDetailsIdForPhoneNumber);
        userDetailsToPhoneNumbers.setName("phoneNumbers"); // one-to-many (userDetails.getListOfPhoneNumbers)

    }

    private static Entity addPhoneNumber(Schema schema) {
        Entity phone = schema.addEntity("DBPhoneNumber");
        phone.addIdProperty().primaryKey().autoincrement();
        phone.addStringProperty("phoneNumber").notNull().unique();
        return phone;
    }

    private static Entity addUserDetails(Schema schema) {
        Entity userDetails = schema.addEntity("DBUserDetails");
        userDetails.addIdProperty().primaryKey().autoincrement();
        userDetails.addStringProperty("nickName").notNull();
        userDetails.addStringProperty("firstName").notNull();
        userDetails.addStringProperty("lastName").notNull();
        userDetails.addDateProperty("birthDate");
        userDetails.addStringProperty("gender");
        userDetails.addStringProperty("country");
        return userDetails;
    }

    private static Entity addUser(Schema schema) {
        Entity user = schema.addEntity("DBUser");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("email").notNull().unique();
        user.addStringProperty("password").notNull().unique();
        return user;
    }


}
