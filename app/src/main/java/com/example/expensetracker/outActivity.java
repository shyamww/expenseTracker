package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

            purposeText = findViewById(R.id.textPurpose_o);
            amountText = findViewById(R.id.textAmount_o);
            addData = findViewById(R.id.btnAdd_o);

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
                    intent.putExtra("message_o", toSend);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }
    }
