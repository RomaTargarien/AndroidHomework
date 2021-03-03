package com.example.localisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> array = new ArrayList<>();
    ArrayAdapter spinnerAdapter;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();

        Button ru_btn = (Button) findViewById(R.id.buttonRus);
        ru_btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Locale locale = new Locale("ru");
                changeLocale(locale);
            }
        });
        Button en_btn = (Button) findViewById(R.id.buttonEn);
        en_btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Locale locale = new Locale("en");
                changeLocale(locale);
            }
        });

        Button ok_button = findViewById(R.id.buttonOK);
        ok_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivityImplicit.class);
                String item = spinner.getSelectedItem().toString();
                intent.putExtra("Item", item);
                EditText editText = findViewById(R.id.editTextAmount);
                String amount = editText.getText().toString();
                intent.putExtra("Amount", Integer.parseInt(amount));
                startActivity(intent);
            }
        });


    }
    private void changeLocale(Locale locale)
    {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources()
                .updateConfiguration(configuration,
                        getBaseContext()
                                .getResources()
                                .getDisplayMetrics());
        setTitle(R.string.app_name);

        TextView tv = (TextView) findViewById(R.id.textViewAmount);
        tv.setText(R.string.lbl_amount);

        tv = (TextView) findViewById(R.id.textViewProduct);
        tv.setText(R.string.lbl_product);


        Button btn = (Button) findViewById(R.id.buttonOK);
        btn.setText(R.string.btn_ok);
    }
    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        array.add("TV");
        array.add("Phone");
        array.add("Microwave");
        array.add("Laptop");
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}