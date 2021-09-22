package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class outActivity extends AppCompatActivity {

        private EditText purposeText, amountText;
        private Button addData;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_out);
            // calling the action bar
            ActionBar actionBar = getSupportActionBar();
            //showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            setTitle("Out Money");

            purposeText = findViewById(R.id.textPurpose_o);
            amountText = findViewById(R.id.textAmount_o);
            addData = findViewById(R.id.btnAdd_o);

            addData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String purpose = purposeText.getText().toString();
                    String Amount = amountText.getText().toString();


                    if(purpose.isEmpty()){
                        purpose = "-";
                    }
                    if(Amount.isEmpty()){
                        Amount = "0.0";
                    }
                    String toSend = purpose + " " + Amount;
                    Intent intent = new Intent();
                    intent.putExtra("message_o", toSend);
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


//    //for action bar icons
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


}
