package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActivity = true;
    //0 --> X
    //1 --> O

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {    {0,1,2}, {3,4,5}, {6,7,8},
                                {0,3,6}, {1,4,7}, {2,5,8},
                                {0,4,8}, {2,4,6}};
    // State meaning:
    //   0 --> X
    //   1 --> O
    //   2 --> Null

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!gameActivity) {
            gameReset(view);
        }

        if(gameState[tappedImage]==2 && gameActivity) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.Status);
                status.setText("O's Turn-Tap to Play");

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.Status);
                status.setText("X's Turn-Tap to Play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition: winPositions)
        {
            if(gameState[winPosition[0]]==gameState[winPosition[1]]&&
                    gameState[winPosition[1]]==gameState[winPosition[2]]
                            &&gameState[winPosition[0]]!=2) {

                    //someone has won the game
                    //if array number has 0 in it then O has won else for 1, X has won the game
                    String winnerStr;
                    gameActivity = false;
                    if(gameState[winPosition[0]]==0)
                            winnerStr = "Yayy!! X has won.";
                    else
                        winnerStr = "Yayy!! O has won.";

                    //Update the status bar to display the winner

                TextView status = findViewById(R.id.Status);
                status.setText(winnerStr);
            }
        }

        boolean empty_square = false;
        for(int squareState: gameState )
        {
            if(squareState==2)
                empty_square=true;
        }

        if(empty_square==false && gameActivity==true)
        {
            //It's a draw
            String winnerStr;
            gameActivity = false;
            winnerStr = "It is a Draw!!";
            TextView status = findViewById(R.id.Status);
            status.setText(winnerStr);
        }
    }

    public void gameReset(View view) {

            gameActivity = true;
            activePlayer = 0;
        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
            ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

            TextView status = findViewById(R.id.Status);
            status.setText("X's Turn-Tap to Play");
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}