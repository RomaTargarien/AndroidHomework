package com.example.localisation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityImplicit extends AppCompatActivity {

    String subject = "Order";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_implicit);
        setTitle("Implicit");

        Intent recievedOrderIntent = getIntent();
        String item = recievedOrderIntent.getStringExtra("Item");
        int amount = recievedOrderIntent.getIntExtra("Amount",0);
        TextView textView = findViewById(R.id.textViewImpicit);
        textView.setText("Your product is " + item + " in the amount of " + amount);

        Button button = findViewById(R.id.buttonSend);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, textView.getText());
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

    }
}