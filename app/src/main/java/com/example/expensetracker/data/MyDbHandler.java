package com.example.expensetracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_DETAIL
                + " TEXT, " + Params.KEY_AMOUNT + " TEXT, " + Params.KEY_CHECK_UPDATE + " TEXT, "
                + Params.KEY_IN_AND_OUT + " TEXT" + ")";

        Log.d("dbFirst", "my first db created: " + create);
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addExpense(Expense expense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Params.KEY_DETAIL, expense.getDetail());
        values.put(Params.KEY_AMOUNT, expense.getAmount());
        values.put(Params.KEY_CHECK_UPDATE, expense.getCheck_for_update());
        values.put(Params.KEY_IN_AND_OUT, expense.getCheck_for_in_out());


        try {
            //  Block of code to try
            db.insert(Params.TABLE_NAME, null, values);
            Log.d("dbFirst ", "Successfully Inserted");
        }
        catch(Exception e) {
            //  Block of code to handle errors
            Log.d("dbFirst ", "Error occour");
        }
        db.close();
    }

    public List<Expense> getAllExpenses(){
        List<Expense> expenseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String x = this.getDatabaseName();
//        Log.d("dbFirst", "Database name is: " + x);

        //generate query to read the db
        String select = "Select * from "+ Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop
        if(cursor.moveToFirst()){
            do{
                Expense expense = new Expense();
                expense.setId(Integer.parseInt(cursor.getString(0)));
                expense.setDetail(cursor.getString(1));
                expense.setAmount(cursor.getString(2));
                expense.setCheck_for_update(cursor.getString(3));
                expense.setCheck_for_in_out(cursor.getString(4));
                expenseList.add(expense);

            }while(cursor.moveToNext());
        }
        return expenseList;
    }

    public void updateExpense(int idd, String newDetail, String newAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(Params.KEY_DETAIL,newDetail);
        data.put(Params.KEY_AMOUNT,newAmount);
        db.update(Params.TABLE_NAME, data, Params.KEY_ID +"=" +idd, null);
        db.close();
    }

    public void make_check_column_yes_for_delete(int idd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(Params.KEY_CHECK_UPDATE,"Yes");
        db.update(Params.TABLE_NAME, data, Params.KEY_ID +"=" +idd, null);
        db.close();
    }

    public void delet_the_row_from_update_activity(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        String ckk = "Yes";
        db.delete(Params.TABLE_NAME,"check_for_update=?",new String[]{"Yes"});
        db.close();
    }

}
