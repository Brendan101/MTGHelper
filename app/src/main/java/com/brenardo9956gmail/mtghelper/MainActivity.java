package com.brenardo9956gmail.mtghelper;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int STARTING_LIFE = 20;
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;
    public static final int LIFE_LOWER_BOUND = -10;
    public static final int LIFE_UPPER_BOUND = 100;
    
    Button playerOneInc;
    Button playerOneDec;
    TextView playerOneLife;
    Button playerTwoInc;
    Button playerTwoDec;
    TextView playerTwoLife;
    Button resetButton;
    Handler myhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //player one 
        playerOneInc = (Button)findViewById(R.id.playerOneInc);
        playerOneInc.setOnClickListener(this);
        playerOneDec = (Button)findViewById(R.id.playerOneDec);
        playerOneDec.setOnClickListener(this);
        playerOneLife = (TextView)findViewById(R.id.playerOneLife);

        //player two 
        playerTwoInc = (Button)findViewById(R.id.playerTwoInc);
        playerTwoInc.setOnClickListener(this);
        playerTwoDec = (Button)findViewById(R.id.playerTwoDec);
        playerTwoDec.setOnClickListener(this);
        playerTwoLife = (TextView)findViewById(R.id.playerTwoLife);

        //other
        resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
        
    }

    private class LifeWork implements Runnable{

        private int life, player;

        private LifeWork(int life, int player){

            this.life = life;
            this.player = player;

        }

        public void run(){

            //bounds checks (mostly for display purposes)
            if(life > LIFE_LOWER_BOUND && life < LIFE_UPPER_BOUND) {

                if (player == PLAYER_ONE) {
                    playerOneLife.setText(Integer.toString(life));
                } else if (player == PLAYER_TWO) {
                    playerTwoLife.setText(Integer.toString(life));
                }

            }

        }

    }

    @Override
    public void onClick(View view) {

        int newLife;

        switch(view.getId()){

            case R.id.playerOneInc:
                newLife = Integer.parseInt(playerOneLife.getText().toString()) + 1;
                myhandler.post(new LifeWork(newLife, PLAYER_ONE));
                break;
            case R.id.playerOneDec:
                newLife = Integer.parseInt(playerOneLife.getText().toString()) - 1;
                myhandler.post(new LifeWork(newLife, PLAYER_ONE));
                break;
            case R.id.playerTwoInc:
                newLife = Integer.parseInt(playerTwoLife.getText().toString()) + 1;
                myhandler.post(new LifeWork(newLife, PLAYER_TWO));
                break;
            case R.id.playerTwoDec:
                newLife = Integer.parseInt(playerTwoLife.getText().toString()) - 1;
                myhandler.post(new LifeWork(newLife, PLAYER_TWO));
                break;
            case R.id.resetButton:
                myhandler.post(new LifeWork(STARTING_LIFE, PLAYER_ONE));
                myhandler.post(new LifeWork(STARTING_LIFE, PLAYER_TWO));
                break;
        }

    }
}
