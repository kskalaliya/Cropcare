package com.example.cropandroidapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.cropandroidapp.adeptors.OrderAdaptor;
import com.example.cropandroidapp.adeptors.OrderListAdaptor;
import com.example.cropandroidapp.databinding.ActivityOrderListBinding;
import com.example.cropandroidapp.models.OrderModel;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    ActivityOrderListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderListBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        DBHelper db = new DBHelper(this);

        binding.listAddress.setText("Deliver Address : "+OrderModel.getAddress());
        ArrayList<OrderModel> list = db.getOrders(db.getUserId(OrderModel.getUsername()));

        binding.listTotalAmount.setText("Total Amount: "+Double.toString(
                db.totalAmount(db.getUserId(OrderModel.getUsername()))));

        OrderListAdaptor adaptor = new OrderListAdaptor(list,this);

        binding.listRecyclerView.setAdapter(adaptor);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.listRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.order_list_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.homePage2:
                startActivity(new Intent(OrderListActivity.this,MainActivity.class));
                break;

            case R.id.logout1:
                startActivity(new Intent(OrderListActivity.this,LoginActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}