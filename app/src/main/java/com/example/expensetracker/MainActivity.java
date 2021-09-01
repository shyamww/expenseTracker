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
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int tIn=0,tOut=0;
    String myaddedGlobaldata;
    ArrayList<String> expenses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView myListView = findViewById(R.id.list_item);

        expenses.add("1");
        expenses.add("2");
        expenses.add("3");
        expenses.add("4");
//        expenses.add("5");
//        expenses.add("6");
//        expenses.add("7");
//        expenses.add("8");
//        expenses.add("9");
//        expenses.add("10");
//        expenses.add("11");
//        expenses.add("12");
//        expenses.add("13");
//        expenses.add("14");


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenses);

        myListView.setAdapter(arrayAdapter);
        Button addItem = findViewById(R.id.btnIn);
        Button removeItem = findViewById(R.id.btnOut);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = expenses.size();
                goToInActivity();

            }
        });


        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = expenses.size();
                goToOutActivity();

            }
        });


        //delete an item from the list
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int which_pos = i;
                String dataOfThisRow = expenses.get(which_pos);
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage(dataOfThisRow)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                expenses.remove(which_pos);
                                arrayAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

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
//        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK)
        {
            tIn = tIn + 1;
            myaddedGlobaldata = "+ " + data.getStringExtra("message") ;
            expenses.add(myaddedGlobaldata);
            Toast.makeText(this, myaddedGlobaldata, Toast.LENGTH_SHORT).show();
        }
        if(requestCode == 1000 && resultCode == RESULT_OK)
        {
            tOut = tOut +1;
            myaddedGlobaldata = "- " + data.getStringExtra("message_o");
            expenses.add(myaddedGlobaldata);
            Toast.makeText(this, myaddedGlobaldata, Toast.LENGTH_SHORT).show();
        }
        calculateTotal();
    }



    private void calculateTotal() {
        TextView totalVal = findViewById(R.id.totalInOut);
        String s="Total In " + Integer.toString(tIn) + "  Total Out "+ Integer.toString(tOut);
        totalVal.setText(s);
    }

}
