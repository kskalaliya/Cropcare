package com.example.cropandroidapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cropandroidapp.databinding.ActivityDetailBinding;
import com.example.cropandroidapp.databinding.ActivityLogin2Binding;
import com.example.cropandroidapp.databinding.ActivityRegisterBinding;
import com.example.cropandroidapp.models.OrderModel;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity = Integer.parseInt(binding.quantity.getText().toString());
                binding.quantity.setText(Integer.toString((quantity+1)));

                int p = Integer.parseInt(binding.detailPrise.getText().toString());

                p = p + p/quantity;
                binding.detailPrise.setText(Integer.toString(p));
            }
        });

        binding.substract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(binding.quantity.getText().toString());

                if(quantity == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                    builder.setTitle("Alert !");
                    builder.setMessage("Quantity should be >= 1");
                }

                else {
                    binding.quantity.setText(Integer.toString((quantity - 1)));

                    int p = Integer.parseInt(binding.detailPrise.getText().toString());

                    p = p - p / quantity;
                    binding.detailPrise.setText(Integer.toString(p));
                }
            }
        });

        if(getIntent().getIntExtra("type",0)==1){

            final int image = getIntent().getIntExtra("image",0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String description = getIntent().getStringExtra("desc");
            final String name = getIntent().getStringExtra("name");

            binding.detailImage.setImageResource(image);
            binding.detailPrise.setText(Integer.toString(price));
            binding.detailDescription.setText(description);
            binding.medicineName.setText(name);



            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isInserted = helper.insertOrder(
                            Integer.parseInt(binding.quantity.getText().toString()),
                            Integer.parseInt(binding.detailPrise.getText().toString()),
                            image,
                            description,
                            name,
                            helper.getUserId(OrderModel.getUsername())
                    );

                    if(isInserted){
                        Toast.makeText
                                (DetailActivity.this,"Data Inserted Successfully",
                                        Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText
                                (DetailActivity.this,"Error During Insertion",
                                        Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);

            binding.detailImage.setImageResource(cursor.getInt(3));
            binding.detailPrise.setText(Integer.toString(cursor.getInt(2)));
            binding.quantity.setText(cursor.getInt(1) + "");
            binding.detailDescription.setText(cursor.getString(4));
            binding.medicineName.setText(cursor.getString(5));

            binding.insertBtn.setText("UPDATE ORDER");

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {

                                                         boolean isUpdated = helper.updateOrder(
                                                                 Integer.parseInt(binding.quantity.getText().toString()),
                                                                 Integer.parseInt(binding.detailPrise.getText().toString()),
                                                                 cursor.getInt(3),
                                                                 binding.detailDescription.getText().toString(),
                                                                 binding.medicineName.getText().toString(),
                                                                 id
                                                         );

                                                         if (isUpdated) {
                                                             Toast.makeText
                                                                     (DetailActivity.this, "Data Updated Successfully",
                                                                             Toast.LENGTH_SHORT).show();

                                                             Intent intent = new Intent(DetailActivity.this,OrderActivity.class);
                                                             startActivity(intent);

                                                         } else {
                                                             Toast.makeText
                                                                     (DetailActivity.this, "Error During Updating",
                                                                             Toast.LENGTH_SHORT).show();
                                                         }
                                                     }
                                                 }
            );
        }



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
                startActivity(new Intent(DetailActivity.this,MainActivity.class));
                break;

            case R.id.orders1:
                startActivity(new Intent(DetailActivity.this,OrderActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}