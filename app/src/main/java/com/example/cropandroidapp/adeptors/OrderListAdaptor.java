package com.example.cropandroidapp.adeptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cropandroidapp.DBHelper;
import com.example.cropandroidapp.R;
import com.example.cropandroidapp.models.OrderModel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderListAdaptor extends RecyclerView.Adapter<OrderListAdaptor.ViewListHolderOrder>{

    ArrayList<OrderModel> list;
    Context context;
    DBHelper db;

    public OrderListAdaptor(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewListHolderOrder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_list_sample,parent,false);

        return new OrderListAdaptor.ViewListHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewListHolderOrder holder, int position) {

        final OrderModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.orderPrice.setText(model.getOrderPrice());
        holder.orderQuantity.setText(model.getOrderQuantity());
        holder.soldItemName.setText(model.getSoldItemName());

        db = new DBHelper(context);
        db.deleteOrder(Integer.parseInt(model.getOrderNumber()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewListHolderOrder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView soldItemName, orderQuantity, orderPrice;

        public ViewListHolderOrder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.listImage);
            soldItemName = itemView.findViewById(R.id.listItemName);
            orderQuantity = itemView.findViewById(R.id.orderQuantity);
            orderPrice = itemView.findViewById(R.id.listPrice);

        }
    }
}
