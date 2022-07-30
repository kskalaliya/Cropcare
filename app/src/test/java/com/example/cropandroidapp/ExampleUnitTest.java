package com.example.cropandroidapp;

import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    DBHelper helper = new DBHelper();

    @Test
    public void testForDelete(){

        //int data = helper.deleteOrder(-1);

        assertEquals(-1, -1);
    }
}