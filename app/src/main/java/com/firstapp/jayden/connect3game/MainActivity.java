package com.firstapp.jayden.connect3game;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// 0 is yellow, 1 is red, 2 is empty
    int [] gameState= {2,2,2,2,2,2,2,2,2};
    int [] [] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlay = 0;
    boolean startGame = true;
    public void dropIn (View view){
        ImageView counter = (ImageView) view;//target whichever image that apply this onclick fuction

        int tappedCounter = Integer.parseInt(counter.getTag().toString());//convert tag counter to int
        if(gameState[tappedCounter] == 2 && startGame){//we start only all columns are empty

            gameState[tappedCounter] = activePlay;//track which column is red or yellow in the array
            //if winning position is 012 the actual tappedCounter is tag 0/1/2

            counter.setTranslationY(-1500);//make sure image is out of screen
            if(activePlay == 0)//yellow and red alternate
            {
                counter.setImageResource(R.drawable.yellow);//set to yellow image
                activePlay = 1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlay = 0;
            }


            counter.animate().translationYBy(1500).setDuration(300);//drop in animation
            for(int[] winningPosition :winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){
                    //in the winningPosition array each first value if is red then second and third value must be red to win and it can't be empty
                    startGame = false;
                    String winner = "";
                    if(activePlay == 0)
                    {
                        winner = "Red";
                    }else{
                        winner = "Yellow";
                    }
                    Toast.makeText(this,winner + " won the game",Toast.LENGTH_SHORT).show();
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }

        }

    }

public void playAgain(View view){
    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


    playAgainButton.setVisibility(View.INVISIBLE);
    winnerTextView.setVisibility(View.INVISIBLE);
    GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
    for(int i=0; i<gridLayout.getChildCount();i++)//loop through each
    {
        ImageView count = (ImageView) gridLayout.getChildAt(i);
        count.setImageDrawable(null);//delete images
    }
    //set back the default
    for(int i = 0; i < gameState.length; i++)
    {
        gameState[i] = 2;
    }
    int activePlay = 0;
    boolean startGame = true;
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
