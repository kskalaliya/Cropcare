package com.example.cropandroidapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cropandroidapp.models.OrderModel;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button signin,signup;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signin = findViewById(R.id.btnsignin1);
        signup = findViewById(R.id.btnsignup1);
        db = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username1 = username.getText().toString();
                String password1 = password.getText().toString();

                if(username1.trim().isEmpty()){
                    username.setError("Please Enter UserName");
                }
                else if(password1.trim().isEmpty()){
                    password.setError("Please Enter Password");
                }
                else{

                    if(db.checkUsernamePassword(username1,password1)){

                        OrderModel orderModel = new OrderModel();
                        orderModel.setUsername(username1);

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Wrong Credential",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}