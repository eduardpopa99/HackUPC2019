package com.example.vuelingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonStart){
            Log.d("TAG", "onClick: works!");
            finish();
            startActivity(new Intent(getApplicationContext(), GameActivity.class));
        }
    }
}
