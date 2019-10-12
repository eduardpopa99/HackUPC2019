package com.example.vuelingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart;
    Button buttonInfo;
    ImageView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);

        buttonInfo = findViewById(R.id.buttonInfo);
        buttonInfo.setOnClickListener(this);

        info = findViewById(R.id.info);


    }

    @Override
    public void onClick(View v) {
        if(v == buttonStart){
            Log.d("TAG", "onClick: works!");
            finish();
            startActivity(new Intent(getApplicationContext(), GameActivity.class));
        }
        else if(v == buttonInfo){

           if(info.getVisibility()==View.VISIBLE){
               info.setVisibility(View.INVISIBLE);
               buttonStart.setVisibility(View.VISIBLE);

           }
           else{
               info.setVisibility(View.VISIBLE);
               buttonStart.setVisibility(View.INVISIBLE);
           }


        }
    }
}
