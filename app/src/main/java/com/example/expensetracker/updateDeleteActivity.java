package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class updateDeleteActivity extends AppCompatActivity {

    private EditText purposeText, amountText;
    private Button updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        purposeText = findViewById(R.id.textPurpose_d);
        amountText = findViewById(R.id.textAmount_d);
        updateData = findViewById(R.id.btn_update);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Update");


        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String purpose = purposeText.getText().toString();
                String Amount = amountText.getText().toString();
                String toSend = purpose + " " + Amount;
                Intent intent = new Intent();
                intent.putExtra("message_u", toSend);
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
            case R.id.add:
                Toast.makeText(this, "delete is clicked", Toast.LENGTH_SHORT).show();
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    //for action bar icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

}
