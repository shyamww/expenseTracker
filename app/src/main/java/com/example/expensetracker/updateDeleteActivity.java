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

    private EditText purposeField, amountField;
    private Button updateButton;
    String toSendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Update");

        purposeField = findViewById(R.id.textPurpose_update);
        amountField = findViewById(R.id.textAmount_update);
        updateButton = findViewById(R.id.btn_update);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
            putDataToFields(value);
            //The key argument here must match that used in the other activity
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String purpose = purposeField.getText().toString();
                String Amount = amountField.getText().toString();
                toSendData = purpose + " " + Amount;
                String value = toSendData;
                String lastWord = value.substring(value.lastIndexOf(" ")+1);
                String firstWord = value.contains(" ") ? value.split(" ")[0] : value;

//                purposeField.setText(firstWord);
//                amountField.setText(lastWord);
                Toast.makeText(updateDeleteActivity.this, firstWord + " "+ lastWord, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(updateDeleteActivity.this, MainActivity.class);
                intent.putExtra("backtomain",toSendData);
                startActivity(intent);
            }
        });

    }

    private void putDataToFields(String value) {
        String lastWord = value.substring(value.lastIndexOf(" ")+1);
        String firstWord = value.contains(" ") ? value.split(" ")[0] : value;

        purposeField.setText(firstWord);
        amountField.setText(lastWord);



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
                updateThisItem();
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateThisItem() {


    }

    //for action bar icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

}
