package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inActivity extends AppCompatActivity {

    private EditText purposeText, amountText;
    private Button addData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("In Money");

        purposeText = findViewById(R.id.textPurpose);
        amountText = findViewById(R.id.textAmount);
        addData = findViewById(R.id.btnAdd);


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String purpose = purposeText.getText().toString();
                String Amount = amountText.getText().toString();
                String toSend = purpose + " " + Amount;

                Intent intent = new Intent();
                intent.putExtra("message", toSend);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
