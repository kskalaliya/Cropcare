package com.example.cropandroidapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cropandroidapp.models.OrderModel;

public class AddressActivity extends AppCompatActivity {

    EditText address;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        address = findViewById(R.id.addressId);
        submitButton = findViewById(R.id.btnSubmitAddress);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address1 = address.getText().toString();

                if(address1.trim().isEmpty()){
                    address.setError("Please Enter Address");
                }
                else{
                    OrderModel.setAddress(address1);

                    Intent intent = new Intent(AddressActivity.this, OrderListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.homePage3:
                startActivity(new Intent(AddressActivity.this,MainActivity.class));
                break;

            case R.id.orders1:
                startActivity(new Intent(AddressActivity.this,OrderActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}