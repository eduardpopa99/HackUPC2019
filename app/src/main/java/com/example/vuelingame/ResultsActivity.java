package com.example.vuelingame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    String p1, p2;
    int s1, s2 ;

    Button home, newGame;

    TextView textViewP1, textViewP2, textViewS1, textViewS2;
    DatabaseReference databaseReference;

    String score1, score2;
    String player;

    private Boolean action = false;

    private void textViewImplement(){
        textViewS1.setText(score1);
        textViewS2.setText(score2);
        if(player.equals("1")){
            textViewP1.setText("YOU");
            textViewP2.setText("OPPONENT");
        }
        else{
            textViewP2.setText("YOU");
            textViewP1.setText("OPPONENT");
        }
    }

    private void configFireBase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("match");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!action) {
                    score1 = dataSnapshot.getValue(Match.class).getScore1();
                    score2 = dataSnapshot.getValue(Match.class).getScore2();
                    if((!score2.equals("Waiting...") && !score1.equals("Waiting...")) && (!score2.equals("-5") && !score1.equals("-5")))
                            action = true;

                    textViewImplement();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        player = intent.getStringExtra("player");
        Log.d("TAG", "getString: " + player);

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

        configFireBase();

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
