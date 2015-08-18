package com.example.giangdam.greendaoex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.giangdam.greendaoex.dao.DBPhoneNumber;
import com.example.giangdam.greendaoex.dao.DBUser;
import com.example.giangdam.greendaoex.dao.DBUserDetails;
import com.example.giangdam.greendaoex.manager.DatabaseManager;
import com.example.giangdam.greendaoex.manager.IDatabaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private List<DBUser> userList;
    /**
     * Manages the database for this application..
     */
    private IDatabaseManager databaseManager;

    TextView lblContent;
    Button btnInsert, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init database manager
        databaseManager = new DatabaseManager(this);
        userList = new ArrayList<DBUser>();
        lblContent = (TextView)findViewById(R.id.lblContent);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnView =(Button)findViewById(R.id.btnView);

        btnView.setOnClickListener(this);
        btnInsert.setOnClickListener(this);









    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsert:
                // create a random user object
                DBUser user = new DBUser();
                user.setEmail(UUID.randomUUID().toString() + "@email.com");
                user.setPassword("defaultPass");

                // insert that user object to our DB
                user = databaseManager.insertUser(user);

                // Create a random userDetails object
                DBUserDetails userDetails = new DBUserDetails();
                userDetails.setBirthDate(new Date());
                userDetails.setCountry("World");
                userDetails.setFirstName(UUID.randomUUID().toString());
                userDetails.setLastName(UUID.randomUUID().toString());
                userDetails.setGender("MALE");
                userDetails.setNickName(UUID.randomUUID().toString());
                userDetails.setUserId(user.getId());
                userDetails.setUser(user);

                // insert or update this userDetails object to our DB
                userDetails = databaseManager.insertOrUpdateUserDetails(userDetails);

                // link userDetails Key to user
                user.setDetailsId(userDetails.getId());
                user.setDetails(userDetails);
                databaseManager.updateUser(user);

                // create a dynamic list of phone numbers for the above object
                for (int i = 0; i <2; i++) {
                    DBPhoneNumber phoneNumber = new DBPhoneNumber();
                    phoneNumber.setPhoneNumber(UUID.randomUUID().toString());
                    phoneNumber.setDetailsId(userDetails.getId());

                    // insert or update an existing phone number into the database
                    databaseManager.insertOrUpdatePhoneNumber(phoneNumber);
                }

                break;
            case R.id.btnView:
                userList = databaseManager.listUsers();
                for(int i = 0; i< userList.size(); i++){
                    lblContent.setText(userList.get(i).getEmail() + userList.get(i).getDetails().getFirstName() + "\n");
                }
                break;
            default:
                break;
        }
    }
}
