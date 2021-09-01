package com.example.expensetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int tIn=0,tOut=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.list_item);
        final ArrayList<String> expenses = new ArrayList<>();
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
                tIn = tIn + 1;
                Toast.makeText(MainActivity.this, Integer.toString(val), Toast.LENGTH_SHORT).show();
                expenses.add("added money");
                arrayAdapter.notifyDataSetChanged();

                calculateTotal();
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = expenses.size();
                tOut = tOut +1;
                Toast.makeText(MainActivity.this, Integer.toString(val), Toast.LENGTH_SHORT).show();
                expenses.add("removed money");
                arrayAdapter.notifyDataSetChanged();
                calculateTotal();

            }
        });

    }

    private void calculateTotal() {
        TextView totalVal = findViewById(R.id.totalInOut);
        String s="Total In " + Integer.toString(tIn) + "  Total Out "+ Integer.toString(tOut);
        totalVal.setText(s);
    }

}
