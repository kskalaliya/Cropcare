package com.example.cropandroidapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.cropandroidapp.adeptors.MainAdaptor;
import com.example.cropandroidapp.adeptors.OrderAdaptor;
import com.example.cropandroidapp.databinding.ActivityMainBinding;
import com.example.cropandroidapp.models.MainModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.coregan,"Coregan","60","good for crop"));
        list.add(new MainModel(R.drawable.defenco,"Defenco","80","Authentic Japanese Product Absorbed by the leaves and roots"));
        list.add(new MainModel(R.drawable.plantic,"plantic","600","Plantic Total Plant Care 3 in 1, Fungicide, Miticide, Insecticide"));
        list.add(new MainModel(R.drawable.cropmax,"CropMax","180","Crop[ promotor"));
        list.add(new MainModel(R.drawable.defenco,"Waarrant","100","Stem borer, Brown plant hopper, Green leaf hopper Rice leaf"));

        MainAdaptor adaptor = new MainAdaptor(list,this);
        binding.recycleview.setAdapter(adaptor);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.recycleview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;

            case R.id.logout:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;

            case R.id.help:
                startActivity(new Intent(MainActivity.this,HelpActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}