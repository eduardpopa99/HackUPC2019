package com.example.vuelingame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //EditText edtjugador1 edtjugador2 edtpuntuacio1 edtpuntuacio2
    FirebaseDatabase database;
    DatabaseReference jugadors;


    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        jugadors = database.getReference("jugadors");
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
