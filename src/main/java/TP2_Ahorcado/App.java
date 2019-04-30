package TP2_Ahorcado;

import java.util.Random;
public class App
{
    private static Player [] players;
    //how many player will be playing at the same time
    private static final int playerCount = 2;

    public static void main(String[] args)
    {
        //starts jdbc to choose a word from the database
        JDBC jdbc = new JDBC();

        //generate a random int between 1 and 10 to look for that id on the list of words stored in the database
        Random randomGenerator;
        randomGenerator  = new Random();

        //trae una palabra de la bdd con un id entre 1 y 10
        int randomInt = randomGenerator.nextInt((9)+1);

        //create a game with a randomly chose word to win
        Word wordToGuess = jdbc.chooseWord(randomInt);
        //show the word which the player will use to play
        System.out.println("The chosed word for the game is " + wordToGuess);
        //each player has 27 lifes in order for them to win no matter what and they are assigned with the same word to
        //play
        players[0]= new Player("Austin",27,wordToGuess);
        players[1] = new Player("George",27,wordToGuess);

        //each player implemments the interface Runnable and starts to play using the overrided method run()
        for(int i = 0; i < playerCount; i++)
        {
            players[i].run();
        }

    }
}
