package com.example.cropandroidapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.cropandroidapp.adeptors.MainAdaptor;
import com.example.cropandroidapp.adeptors.OrderAdaptor;
import com.example.cropandroidapp.databinding.ActivityLogin2Binding;
import com.example.cropandroidapp.databinding.ActivityMainBinding;
import com.example.cropandroidapp.databinding.ActivityOrderBinding;
import com.example.cropandroidapp.models.MainModel;
import com.example.cropandroidapp.models.OrderModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        DBHelper db = new DBHelper(this);

        ArrayList<OrderModel> list = db.getOrders(db.getUserId(OrderModel.getUsername()));

        binding.totalAmount.setText("Total Amount: "+Double.toString(
                db.totalAmount(db.getUserId(OrderModel.getUsername()))));

        OrderAdaptor adaptor = new OrderAdaptor(list,this);

        binding.ordersRecyclerView.setAdapter(adaptor);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.ordersRecyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.order_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.homePage1:
                startActivity(new Intent(OrderActivity.this,MainActivity.class));
                break;

            case R.id.buy:
                DBHelper db = new DBHelper(this);

                ArrayList<OrderModel> list = db.getOrders(db.getUserId(OrderModel.getUsername()));

                if(list.size() == 0){
                    Toast.makeText(OrderActivity.this,"Order List Is Empty",Toast.LENGTH_SHORT).show();
                }
                else
                startActivity(new Intent(OrderActivity.this,AddressActivity.class));

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}