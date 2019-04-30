package TP2_Ahorcado;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;


public class Player implements Runnable {
   private String name; //player's name
   private Integer lifes; //number of lifes
   private char[] alphabet; //the whole alphabet to choose words from
   private Random randomGenerator; //a random number
   private int alphabetLenght; //how many words he has left in the alphabet
   private long playerGuess; //how many letters he found
   private long wordToGuessLength; //how many letters he has to find
   private Word wordToGuess; //the word he has to guess
   private boolean finished; //has he won yet?
   private JDBC bdd;


    public Player(String name, Integer lifes, Word word) {
        this.name = name;
        this.lifes = lifes;
        this.wordToGuess = word;
        alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        randomGenerator  = new Random();
        alphabetLenght = 26;
        playerGuess = 0;
        bdd = new JDBC();
    }


    public synchronized void tryCharacter(){

        //how long is the word to guess.
        wordToGuessLength=wordToGuess.getWord().length();
        char letra;

        //get a random number to search
        //in the index of the array of characters containing the whole alphabet
        //that is between 0 and the number of letters left on the alphabet
        int randomInt = randomGenerator.nextInt((alphabetLenght));
        //the letter the player chose from the array at random
        letra = alphabet[randomInt];

        //ArrayUtils.remove the character the player is going to try to use.
        ArrayUtils.remove(alphabet, randomInt);
        //the player's alphabet index is down one number
        alphabetLenght--;

        //if the letter matches one inside the word and he hasn't won yet and still have tries
        if((wordToGuess.getWord().indexOf(letra)!=-1)&&(!finished)&&(lifes>0)){
            System.out.println("Player "+name+" tried the letter " +letra+" and was successful.");
            long count = wordToGuess.getWord().chars().filter(ch -> ch == letra).count();
            playerGuess=playerGuess+count;
            if(wordToGuessLength==playerGuess){
                    //if he already found all the letters that form the word to guess
                    finished=true;
                }
        }
        else{
            //the letter he chose wasn't present in the word to guess
            System.out.println("Player "+name+" tried using the letter " +letra+" and was not part of the word.");
            lifes--;
        }

        if (lifes == 0){
            //the player run out of lifes
            System.out.println("Player "+name+" run out of tries.");
        }

        if(finished==true){
            System.out.println("Player "+name+" successfully guessed the word " +wordToGuess);
            bdd.saveWinner(name,wordToGuess.getWord());
        }

    }



    @Override
    public void run() {
      tryCharacter();
    }
}
