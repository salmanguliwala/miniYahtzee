package com.example.salmang.diceout;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // placeholder for result of roll
    TextView textBox;

    //Field to hold score
    int score;

    //Random number generator
    Random rand;

    // holds dice value
    int dice1;
    int dice2;
    int dice3;
    int dice4;
    int dice5;

    // Field to hold score text
    TextView scoreBoard;

    // Arraylist to hold all dice values
    ArrayList<Integer> dices;

    // ArrayList for holding dice images
    ArrayList<ImageView> diceImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //Set starting score
        score = 0;

        //Link instances to widgets
        textBox = (TextView) findViewById(R.id.textBox);
        scoreBoard = (TextView) findViewById(R.id.scoreBoard);

        // Initialize random number generator
        rand = new Random();

        // Create ArrayList for dice values
        dices = new ArrayList<Integer>();

        ImageView dice1Image = (ImageView) findViewById(R.id.dice1Image);
        ImageView dice2Image = (ImageView) findViewById(R.id.dice2Image);
        ImageView dice3Image = (ImageView) findViewById(R.id.dice3Image);
        ImageView dice4Image = (ImageView) findViewById(R.id.dice4Image);
        ImageView dice5Image = (ImageView) findViewById(R.id.dice5Image);

        diceImages = new ArrayList<ImageView>();
        diceImages.add(dice1Image);
        diceImages.add(dice2Image);
        diceImages.add(dice3Image);
        diceImages.add(dice4Image);
        diceImages.add(dice5Image);

        //Greet player
        Toast.makeText(getApplicationContext(), "Welcome to Mini-Yahtzee!", Toast.LENGTH_SHORT).show();
    }

    public void rollDice(View v) {
        //Roll the dice
        dice1 = rand.nextInt(6) + 1;
        dice2 = rand.nextInt(6) + 1;
        dice3 = rand.nextInt(6) + 1;
        dice4 = rand.nextInt(6) + 1;
        dice5 = rand.nextInt(6) + 1;

        dices.clear();
        dices.add(dice1);
        dices.add(dice2);
        dices.add(dice3);
        dices.add(dice4);
        dices.add(dice5);

        for(int numDice = 0; numDice < 5; numDice++){
            String nameOfImage = "die_" + dices.get(numDice) + ".png";

            try {
                InputStream stream = getAssets().open(nameOfImage);
                Drawable diceimg = Drawable.createFromStream(stream, null);
                diceImages.get(numDice).setImageDrawable(diceimg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String rollMsg;
        if(dice1 == dice2 && dice2 == dice3) {
            int tempScore = dice1 * 100;
            rollMsg = "You rolled a triple " + dice1 + ", gaining " + tempScore + " points.";
            score += tempScore;
        } else if (dice1 == dice2 || dice1 == dice3 || dice2 == dice3){
            rollMsg = "You scored a double, gaining 50 points!";
            score += 50;
        } else {
            rollMsg = "You didn't score. Roll again!";
        }

        textBox.setText(rollMsg);
        scoreBoard.setText("Score: " + score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
