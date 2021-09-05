package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensetracker.data.MyDbHandler;

import java.util.ArrayList;

public class updateDeleteActivity extends AppCompatActivity {

    private EditText purposeText, amountText;
    private Button updateData;
    String  check_delete_happen_or_not=" ";
    MyDbHandler db = new MyDbHandler(updateDeleteActivity.this);

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

        //fetching the data of the selected row

        try {
            //  Block of code to try
            ArrayList temp = db.return_the_data_of_the_row_to_update();
            String a = (String) temp.get(0);
            String b = (String) temp.get(1);
            Log.d("dbFirst", temp + a+" "+ b+ "temp");
            purposeText.setText(a);
            amountText.setText(b);
            purposeText.setSelection(purposeText.getText().length());

        }
        catch(Exception e) {
            //  Block of code to handle errors
            Log.d("dbFirst", "Not fetched: " + e);
        }

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returning_function();
            }
        });

    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // back icon
                Log.d("dbFirst", "back arrow is clicked");
                do_yes_to_no_in_last_cloumn();
                this.finish();
                return true;
            case R.id.add: // delete icon
                check_delete_happen_or_not = "Yes";
                db.delet_the_row_from_update_activity();
//                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
                Log.d("dbFirst", "deleted the row form updateDeleteActivity");
                this.finish();
                return true;
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

    public void returning_function()
    {
        String purpose = purposeText.getText().toString();
        String Amount = amountText.getText().toString();
        String toSend = purpose + " " + Amount;// + " " + check_delete_happen_or_not;
        Intent intent = new Intent();
        intent.putExtra("message_u", toSend);
        Log.d("dbFirst", "Updating data: " + toSend);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        do_yes_to_no_in_last_cloumn();
        Log.d("dbFirst", "back is clicked");
        Toast.makeText(getApplication(),"A",Toast.LENGTH_SHORT).show();
    }



    private void do_yes_to_no_in_last_cloumn() {
        db.make_check_column_no_for_delete_without_id();
    }

}
