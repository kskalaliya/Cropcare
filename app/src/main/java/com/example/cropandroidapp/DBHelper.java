package com.example.cropandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.cropandroidapp.models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME= "cropcare_db.db";
    final static int DB_VERSION = 2;

    public DBHelper(){
        super(null,DBNAME,null,DB_VERSION);

    }
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users" +
                "(" +
                "id integer primary key autoincrement," +
                "name text not null,"+
                "username text unique not null," +
                "password text not null," +
                "phoneNo text not null" +
                ")");

        db.execSQL("create table orders" +
                "(" +
                "id integer primary key autoincrement," +
                "quantity int," +
                "price int," +
                "image int," +
                "description text," +
                "medicine_name text," +
                "user_id int," +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists orders");
        db.execSQL("DROP table if exists users");
        onCreate(db);
    }

    public boolean insertOrder(int quantity,int price,int image,String desc, String medName, int user_id){

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("quantity",quantity);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("medicine_name",medName);
        values.put("user_id",user_id);

        long id = db.insert("orders",null,values);

        if(id<=0)
            return false;

        return true;
    }

    public ArrayList<OrderModel> getOrders(int user_id){

        ArrayList<OrderModel> orders = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select id,medicine_name,image,price,quantity from orders where user_id = "+user_id,null);

        if(cursor.getCount()>0){

            cursor.moveToFirst();

            do{
                OrderModel orderModel = new OrderModel();

                orderModel.setOrderImage(cursor.getInt(2));
                orderModel.setOrderNumber(cursor.getInt(0)+"");
                orderModel.setOrderPrice(cursor.getInt(3)+"");
                orderModel.setSoldItemName(cursor.getString(1));
                orderModel.setOrderQuantity(cursor.getInt(4)+"");

                orders.add(orderModel);

            }while(cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return orders;
    }

    public double totalAmount(int user_id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select price from orders where user_id = "+user_id,null);

        double totalAmount = 0;

        if(cursor.getCount()>0){

            cursor.moveToFirst();

            do{
                totalAmount = totalAmount + cursor.getInt(0);

            }while(cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return totalAmount;
    }

    public Cursor getOrderById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from orders where id = "+id,null);

        if(cursor != null)
            cursor.moveToFirst();


        return cursor;
    }

    public boolean updateOrder(int quantity,int price,
                               int image,String desc, String medName,int id){

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("quantity",quantity);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("medicine_name",medName);

        long row = db.update("orders",values,"id = "+id,null);

        if(row<=0)
            return false;

        return true;
    }

    public int deleteOrder(int id){

        SQLiteDatabase db = getReadableDatabase();

        return db.delete("orders","id="+id,null);
    }

    public boolean insertUser(String name,String username,String password,String phoneNo){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("username",username);
        values.put("password",password);
        values.put("phoneNo",phoneNo);

        long id = db.insert("users",null,values);

        if(id<=0)
            return false;

        return true;
    }

    public boolean checkUsername(String username){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?",new String[]{username});

        if(cursor.getCount() >= 1)
            return true;

        return false;
    }

    public boolean checkUsernamePassword(String username, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?",
                new String[]{username,password});

        if(cursor.getCount() >= 1)
            return true;

        return false;
    }

    public int getUserId(String username){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM users WHERE users.username='" + username+"'";

        Cursor  cursor = db.rawQuery(query,null);

        cursor.moveToFirst();

        return cursor.getInt(0);
    }
}
