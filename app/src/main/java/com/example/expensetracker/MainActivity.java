package com.example.expensetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensetracker.data.MyDbHandler;
import com.example.expensetracker.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int tIn=0,tOut=0;
    double total_in= 0.0,total_out= 0.0;
    String myaddedGlobaldata;
    ArrayList<String> expenses = new ArrayList<>();
    MyDbHandler db = new MyDbHandler(MainActivity.this);
    int id_to_update_row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView myListView = findViewById(R.id.list_item);

        //get all the expenses
        List<Expense> allexpense = db.getAllExpenses();
        for(Expense expense: allexpense){
            int id_of_this_row = expense.getId();
            String detail_of_this_row = expense.getDetail();
            String amount_of_this_row = expense.getAmount();
            String in_or_out_of_this_row = expense.getCheck_for_in_out();
            String data_of_this_row = id_of_this_row + " " + detail_of_this_row +" -> " + amount_of_this_row + " = " + in_or_out_of_this_row;
            Log.d("dbFirst",  id_of_this_row+" " + expense.getCheck_for_update());
            expenses.add(data_of_this_row);
        }


        //db things end here
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenses);

        myListView.setAdapter(arrayAdapter);
        Button addItem = findViewById(R.id.btnIn);
        Button removeItem = findViewById(R.id.btnOut);
        Button refresh_list = findViewById(R.id.btn_refresh);


        //refresh button
        refresh_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayAdapter.notifyDataSetChanged();
                fetchTheCompleteList();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        // In button
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = expenses.size();
                goToInActivity();

            }
        });

        // Out button
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = expenses.size();
                goToOutActivity();
            }
        });


        //delete an item from the list
//        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                final int which_pos = i;
//                String dataOfThisRow = expenses.get(which_pos);
//                new AlertDialog.Builder(MainActivity.this)
//                        .setIcon(android.R.drawable.ic_delete)
//                        .setTitle("Are you sure ?")
//                        .setMessage(dataOfThisRow)
//                        .setNeutralButton("Update", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this, "Update successfull", Toast.LENGTH_SHORT).show();
//                                new AlertDialog.Builder(MainActivity.this)
//                                        .setIcon(android.R.drawable.ic_dialog_info)
//                                        .setTitle("Are you sure ?")
//                                        .setMessage("UPDATE HO RHA H")
//                                        .setNegativeButton("No", null)
//                                        .show();
//                            }
//                        })
//                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                expenses.remove(which_pos);
//                                arrayAdapter.notifyDataSetChanged();
//
//                            }
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//                return true;
//
//            }
//        });

        // Click an item
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String row_data = expenses.get(i);
                String a = row_data.contains(" ") ? row_data.split(" ")[0] : row_data;
                int id_of_this_row_in_db =Integer.parseInt(a);
                id_to_update_row = id_of_this_row_in_db;
                db.make_check_column_yes_for_delete(id_to_update_row);
//                Toast.makeText(MainActivity.this, row_data, Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(getApplicationContext(), updateDeleteActivity.class), 1001);
            }

        });

    }

    private void  goToInActivity() {
        startActivityForResult(new Intent(getApplicationContext(), inActivity.class), 999);
    }
    private void goToOutActivity() {
        startActivityForResult(new Intent(getApplicationContext(), outActivity.class), 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        MyDbHandler db = new MyDbHandler(MainActivity.this);
//        super.onActivityResult(requestCode, resultCode, data);
        //In
        if(requestCode == 999 && resultCode == RESULT_OK)
        {
            tIn = tIn + 1;
            myaddedGlobaldata = "+" + data.getStringExtra("message") ;
//            Toast.makeText(this, myaddedGlobaldata, Toast.LENGTH_SHORT).show();
            String detail_data = myaddedGlobaldata.contains(" ") ? myaddedGlobaldata.split(" ")[0] : myaddedGlobaldata;
            String amount_data = myaddedGlobaldata.substring(myaddedGlobaldata.lastIndexOf(" ")+1);

            //Creating data for the db
            Expense e1 = new Expense();
            e1.setDetail(detail_data);
            e1.setAmount(amount_data);
            e1.setCheck_for_update("No");
            e1.setCheck_for_in_out("In");
            //Adding data to the db
            db.addExpense(e1);
        }
        //Out
        if(requestCode == 1000 && resultCode == RESULT_OK)
        {
            tOut = tOut +1;
            myaddedGlobaldata = "-" + data.getStringExtra("message_o");
//            Toast.makeText(this, myaddedGlobaldata, Toast.LENGTH_SHORT).show();
            String detail_data = myaddedGlobaldata.contains(" ") ? myaddedGlobaldata.split(" ")[0] : myaddedGlobaldata;
            String amount_data = myaddedGlobaldata.substring(myaddedGlobaldata.lastIndexOf(" ")+1);
            //Creating data for the db
            Expense e1 = new Expense();
            e1.setDetail(detail_data);
            e1.setAmount(amount_data);
            e1.setCheck_for_update("No");
            e1.setCheck_for_in_out("Out");
            //Adding data to the db
            db.addExpense(e1);
        }
        //Item click or Update
        if(requestCode == 1001 && resultCode == RESULT_OK)
        {
            tIn = tIn + 1;
            myaddedGlobaldata = data.getStringExtra("message_u") ;
            String detail_data = myaddedGlobaldata.contains(" ") ? myaddedGlobaldata.split(" ")[0] : myaddedGlobaldata;
            String amount_data = myaddedGlobaldata.substring(myaddedGlobaldata.lastIndexOf(" ")+1);
            // id is id_to_update_row
            db.updateExpense(id_to_update_row, detail_data, amount_data);

        }
        calculateTotal();
    }

    private void fetchTheCompleteList() {
            List<Expense> allexpense = db.getAllExpenses();
            total_in=total_out=0.0;
            expenses.clear();
            for (Expense expense : allexpense) {
                int id_of_this_row = expense.getId();
                String detail_of_this_row = expense.getDetail();
                String amount_of_this_row = expense.getAmount();
                String in_or_out_of_this_row = expense.getCheck_for_in_out();
                String data_of_this_row = id_of_this_row + " " + detail_of_this_row + " -> " + amount_of_this_row + "==" + in_or_out_of_this_row;
                expenses.add(data_of_this_row);
            }
    }

    private void calculateTotal() {
        TextView totalVal = findViewById(R.id.totalInOut);
        String s="Total In " + Integer.toString(tIn) + "  Total Out "+ Integer.toString(tOut);
        totalVal.setText(s);
    }

}
