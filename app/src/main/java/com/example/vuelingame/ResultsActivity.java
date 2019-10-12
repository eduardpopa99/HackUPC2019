package com.example.vuelingame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    String p1, p2;
    int s1, s2;

    TextView textViewP1, textViewP2, textViewS1, textViewS2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        textViewP1 = findViewById(R.id.textViewPlayer1);
        textViewP2 = findViewById(R.id.textViewPlayer2);
        textViewS1 = findViewById(R.id.textViewScore1);
        textViewS2 = findViewById(R.id.textViewScore2);

    }
}
