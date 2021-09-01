package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inActivity extends Activity {

    private EditText purposeText, amountText;
    private Button addData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);
        purposeText = findViewById(R.id.textPurpose);
        amountText = findViewById(R.id.textAmount);
        addData = findViewById(R.id.btnAdd);


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String purpose = purposeText.getText().toString();
//                String Amount = amountText.getText().toString();
//                String toSend = purpose + " " + Amount;
//
//                Intent intent = new Intent(inActivity.this, MainActivity.class);
//                intent.putExtra("keyname", toSend);
//                startActivity(intent);
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

}
