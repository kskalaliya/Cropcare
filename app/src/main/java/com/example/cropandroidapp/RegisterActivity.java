package com.example.cropandroidapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    EditText name,username,password,repassword, phoneNo;
    Button signup,signin;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        phoneNo = findViewById(R.id.phoneNo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);

        db = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                String repassword1 = repassword.getText().toString();
                String phoneNo1 = phoneNo.getText().toString();

                if(name1.trim().isEmpty()){
                    name.setError("Please Enter name");
                }
                else if(username1.trim().isEmpty()){
                    username.setError("Please Enter User Name");
                }
                else if(db.checkUsername(username1)){
                    username.setError("This type of user already exist");
                }
                else if(password1.trim().isEmpty()){
                    password.setError("Please Enter Password");
                }
                else if(repassword1.trim().isEmpty()){
                    repassword.setError("Please Enter Repassword");
                }
                else if(!repassword1.equals(password1)){
                    repassword.setError("Password are not similar");
                }
                else if(phoneNo1.trim().isEmpty()){
                    phoneNo.setError("Please Enter PhoneNo");
                }
                else if(phoneNo1.trim().length() != 10){
                    phoneNo.setError("Phone Should be have 10 digit");
                }
                else if(!checkPhoneNoContainDigit(phoneNo1.trim())){
                    phoneNo.setError("Phone Number Should Have Digits");
                }
                else{

                    Boolean insert = db.insertUser(name1,username1,password1,phoneNo1);

                    if(insert){
                        Toast.makeText( RegisterActivity.this," Registration Successfully",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText( RegisterActivity.this,"Some Error In Registration",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    static boolean checkPhoneNoContainDigit(String phoneNo){

        for(int i = 0 ;i<phoneNo.length();i++){
            if(!Character.isDigit(phoneNo.charAt(i)))
                return false;
        }

        return true;
    }
}