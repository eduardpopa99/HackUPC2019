package com.example.vuelingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    String p1, p2;
    int s1, s2 ;

    Button home, newGame;

    TextView textViewP1, textViewP2, textViewS1, textViewS2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        textViewP1 = findViewById(R.id.textViewPlayer1);
        textViewP2 = findViewById(R.id.textViewPlayer2);
        textViewS1 = findViewById(R.id.textViewScore1);
        textViewS2 = findViewById(R.id.textViewScore2);


        home=findViewById(R.id.home);
        home.setOnClickListener(this);
        newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==home){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        else if (v==newGame){
            finish();
            startActivity(new Intent(getApplicationContext(), GameActivity.class));
        }

    }

}
