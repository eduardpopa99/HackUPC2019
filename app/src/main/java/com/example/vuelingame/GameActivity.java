package com.example.vuelingame;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnTouchListener;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class GameActivity extends AppCompatActivity {

    // Frame
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth, initialFrameWidth;
    private LinearLayout startLayout;

    // Image
    private ImageView box, black, orange, pink;
    private Drawable imageBoxRight, imageBoxLeft;

    // Size
    private int boxSize;

    // Position
    private float boxX, boxY;
    private float blackX, blackY;
    private float orangeX, orangeY;
    private float pinkX, pinkY;

    // Score
    private TextView scoreLabel, readyLabel, lifeLabel;
    private int timeCount, vida, score;


    // Class
    private Timer timer;
    private Handler handler = new Handler();
    private float difficulty = 1.0f;

    // Status
    private boolean start_flg = false;
    //private boolean action_flg = false;
    private boolean pink_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        box = findViewById(R.id.box);
        black = findViewById(R.id.black);
        orange = findViewById(R.id.orange);
        pink = findViewById(R.id.pink);
        scoreLabel = findViewById(R.id.scoreLabel);
        lifeLabel = findViewById(R.id.lifeLabel);
        readyLabel = findViewById(R.id.readyLabel);

        imageBoxLeft = getResources().getDrawable(R.drawable.box_left);
        imageBoxRight = getResources().getDrawable(R.drawable.box_right);

        score = 0;
        vida=3;
    }

    public void changePos() {

        // Add timeCount
        timeCount += 20;

        if(timeCount%1100 == 0) difficulty += 0.1f;

        // Orange
        orangeY += 12*difficulty;

        float orangeCenterX = orangeX + (float) orange.getWidth() / 2;
        float orangeCenterY = orangeY + (float) orange.getHeight() / 2;

        if (hitCheck(orangeCenterX, orangeCenterY)) {
            Log.d("TAG", "changePos: point?");
            orangeY = frameHeight + 100;
            score += 10;
        }

        if (orangeY > frameHeight) {
            Log.d("TAG", "changePos: perdio?");
            orangeY = -100;
            orangeX = (float) Math.floor(Math.random() * (frameWidth - orange.getWidth()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        // Pink
        if (!pink_flg && timeCount % 10000 == 0) {
            pink_flg = true;
            pinkY = -20;
            pinkX = (float) Math.floor(Math.random() * (frameWidth - pink.getWidth()));
        }

        if (pink_flg) {
            pinkY += 20*difficulty;

            float pinkCenterX = pinkX + (float) pink.getWidth() / 2;
            float pinkCenterY = pinkY + (float) pink.getWidth() / 2;

            if (hitCheck(pinkCenterX, pinkCenterY)) {
                pinkY = frameHeight + 30;

                if(vida < 5) vida++;


            }

            if (pinkY > frameHeight){
                pink_flg = false;
            }
            pink.setX(pinkX);
            pink.setY(pinkY);
        }

        // Black
        blackY += 18*difficulty;

        float blackCenterX = blackX + (float) black.getWidth() / 2;
        float blackCenterY = blackY + (float) black.getHeight() / 2;

        if (hitCheck(blackCenterX, blackCenterY)) {
            blackY = frameHeight + 100;
            --vida;
            // Change FrameWidth
            if (vida==0) {
                gameOver();
            }
        }

        if (blackY > frameHeight) {
            blackY = -100;
            blackX = (float) Math.floor(Math.random() * (frameWidth - black.getWidth()));
        }

        black.setX(blackX);
        black.setY(blackY);

        ////////
        /*
        // Move Box
        if (action_flg) {
            // Touching
            boxX += 14;
            box.setImageDrawable(imageBoxRight);
        } else {
            // Releasing
            boxX -= 14;
            box.setImageDrawable(imageBoxLeft);
        }
        */
        ////////

        // Check box position.
        if (boxX < 0) {
            boxX = 0;
            box.setImageDrawable(imageBoxRight);
        }
        if (frameWidth - boxSize < boxX) {
            boxX = frameWidth - boxSize;
            box.setImageDrawable(imageBoxLeft);
        }

        box.setX(boxX);

        String scoreText = getString(R.string.score) + score;
        String livesText = getString(R.string.lives) + vida;

        scoreLabel.setText(scoreText);
        lifeLabel.setText(livesText);

    }

    public boolean hitCheck(float x, float y) {
        return boxX <= x && x <= boxX + boxSize &&
                boxY <= y && y <= frameHeight;
    }



    public void gameOver() {
        // Stop timer.
        timer.cancel();
        timer = null;
        start_flg = false;

        // Before showing startLayout, sleep 1 second.
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startLayout.setVisibility(View.VISIBLE);
        box.setVisibility(View.INVISIBLE);
        black.setVisibility(View.INVISIBLE);
        orange.setVisibility(View.INVISIBLE);
        pink.setVisibility(View.INVISIBLE);

    }

    //////////
    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (start_flg) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;

            }
        }
        return true;
    }
     */
    ////////////

    public void startGame(View view) {
        int w = gameFrame.getWidth();
        Log.d("TAG", "onCreate: " + w);
        start_flg = true;
        startLayout.setVisibility(View.INVISIBLE);

        if (frameHeight == 0) {
            frameHeight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();
            initialFrameWidth = frameWidth;

            boxSize = box.getHeight();
            boxX = box.getX();
            boxY = box.getY();
        }

        frameWidth = initialFrameWidth;

        boxX = 0;

        box.setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch: " + event.toString());
                final int X = (int) event.getX();
                Log.d("TAG", "onTouch: " + X + " " + boxX);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE) {
                    Log.d("TAG", "onTouch12: " + X + " " + boxX + " " + frameWidth);
                    boxX += X%frameWidth;
                    Log.d("TAG", "onTouch13: " + X + " " + boxX + frameHeight);
                    box.setImageDrawable(imageBoxRight);

                    if (boxX < 0) {
                        boxX = 0;
                        box.setImageDrawable(imageBoxRight);
                    }
                    if (frameWidth - boxSize < boxX) {
                        boxX = frameWidth - boxSize;
                        box.setImageDrawable(imageBoxLeft);
                    }
                }
                return true;
            }
        });

        box.setX(0.0f);
        black.setY(3000.0f);
        orange.setY(3000.0f);
        pink.setY(3000.0f);

        blackY = black.getY();
        orangeY = orange.getY();
        pinkY = pink.getY();

        box.setVisibility(View.VISIBLE);
        black.setVisibility(View.VISIBLE);
        orange.setVisibility(View.VISIBLE);
        pink.setVisibility(View.VISIBLE);

        timeCount = 0;
        score = 0;
        vida = 3;
        String scoreText = getString(R.string.score) + score;
        scoreLabel.setText(scoreText);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start_flg) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }
        }, 0, 20);
    }

    public void quitGame(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    public void returnHome(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}