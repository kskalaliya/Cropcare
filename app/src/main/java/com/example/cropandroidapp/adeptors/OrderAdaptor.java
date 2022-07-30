package com.example.cropandroidapp.adeptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cropandroidapp.*;
import com.example.cropandroidapp.models.OrderModel;

import java.util.ArrayList;

public class OrderAdaptor extends RecyclerView.Adapter<OrderAdaptor.ViewHolderOrder>{

    ArrayList<OrderModel> list;
    Context context;

    public OrderAdaptor(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);

        return new ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOrder holder, int position) {

        final OrderModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.orderPrice.setText(model.getOrderPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(context);

                if(db.deleteOrder(Integer.parseInt(model.getOrderNumber()))>0){

                    Toast.makeText
                            (context,"Data Deleted Successfully",
                                    Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context,OrderActivity.class);
                    context.startActivity(intent);

                }
                else{
                    Toast.makeText
                            (context,"Error In Delete",
                                    Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderOrder extends RecyclerView.ViewHolder{

        ImageView orderImage;
        TextView soldItemName, orderNumber, orderPrice;
        Button deleteButton;
        public ViewHolderOrder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.listImage);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
             orderPrice = itemView.findViewById(R.id.listPrice);

             deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }


}
