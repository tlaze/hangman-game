/*
This program simulates the game hangman. First user enters a word or phrase which becomes converted to dashes.
The second player enters values to solve the phrase.
*/

import java.util.*;//imports scanner
//import java.lang.*;//imports exceptions

class Hangman{
      //Main method to explain rules
      public static void main(String[]args){
      
         System.out.println("***Welcome to my Hangman Program!***");
         System.out.println("***The rules are simple***");
         System.out.println("***The phrase creator enters a secret word/phrase and the number of guesses allowed***");
         System.out.println("***Then the guesser enters values to try to guess the hidden message***");
         System.out.println("***Letters and Numbers are allowed!***");
         System.out.println("***If the guesser guesses the hidden message correctly without losing all their lives they win the game!***");
         System.out.println("***Lets play!***");
         System.out.println();
         System.out.println("***GUESSER LOOK AWAY FROM THE SCREEN***");
         System.out.println();
      
         playerTurn();//jumps to playerTurn method
      }//end main
       
      //Method for player to enter secret phrase and number of guesses  
      public static void playerTurn()throws IllegalStateException{

         Scanner keyboard = new Scanner(System.in);
         
         System.out.print("Enter the secret phrase: ");
         String phrase = keyboard.nextLine().toUpperCase();
         
         boolean repeat = true;
         //loops while repeat is true
         while(repeat){
         
            try{
            
               System.out.print("Enter number of guesses allowed: ");
               int numGuesses = keyboard.nextInt(); 
               if(numGuesses < 1){
                  throw new IllegalStateException();  //throws exception if input is less than 1   
                  }
               repeat = false; 
               guess(phrase,numGuesses);//sends phrase and number of guesses to the guess method.  
               }
            //Exception if user doesn't enter an int   
            catch(InputMismatchException e){
            
               System.out.println("Enter a number only!");
               keyboard.next(); 
               System.out.println();
               }
            //Exception runs if user enters a number less than 1  
            catch(IllegalStateException e){
               System.out.println("Number of guesses must be greater than zero!");
               System.out.println();
               }
            //Exception for generic error   
            catch(Exception e){
               System.out.println(e);
               }
   
            }
  
   }//end playerTurn
   
   //method that runs the gameplay
   public static void guess(String phrase, int numGuesses){
      
      StringBuilder hiddenPhrase = new StringBuilder(phrase);//converts phrase to StringBuilder and converts characters to uppercase.

      //Loops though all values in hiddenPhrase. 
      for(int i = 0; i < hiddenPhrase.length(); i++){
         if(Character.isLetterOrDigit(hiddenPhrase.charAt(i))){
            hiddenPhrase.setCharAt(i,'-');//changes letters or numbers to a dash
            }
         }
      //Moves screen up so guesser can't see mystery prhase
      for(int j = 0; j < 50; j++){
         System.out.println("***CLEARING THE SCREEN---DO NOT SCROLL UP!***");
         }
      System.out.println();
      System.out.println("******Guesser's Turn******");
      System.out.println();
      System.out.println("You have " + numGuesses + " chance(s) to guess the phrase.");
      System.out.println();
      
      Scanner keyboard = new Scanner(System.in);
     
      int correct = 0;//sets accumulator to 0 for when user guesses a value correctly
      
      //Adds 1 to "correct variable" if the value in the phrase is guessed 
      for(int j = 0; j < phrase.length(); j++){
         if(Character.isLetterOrDigit(phrase.charAt(j))){
            correct++;
            }            
         }

      String alreadyGuessed = ""; //Starts string to show values which have been guessed
      
      //Loop runs until user runs out of guesses or guesses the phrase correctly
      while(numGuesses >= 1 && correct > 0){
         try{
            System.out.println(hiddenPhrase);
            
            System.out.print("Take A Guess: ");
            
            char guessedValue = keyboard.nextLine().toUpperCase().charAt(0); //user input returns as an uppercase letter...only first index will be inputted
            alreadyGuessed += guessedValue;//concatenates the value user entered with alreadyGuessed String
         
            int inPhrase = phrase.indexOf(guessedValue); //gets index location of the guessed values
                           
            if (inPhrase == -1){ //indexOf returns -1 if guessedValue isn't in the phrase
            
               numGuesses--;//decreases numGuesses by 1
               System.out.println();
               System.out.println(guessedValue + " is not in the phrase.");
               //Prints number of lives until it reaches zero
               if(numGuesses > 0){
                  System.out.println("Remaining Lives: " + numGuesses);
                  }
               }
            //Runs if user enters a value which was already guessed and decreases numGuesses count by 1
            else if(guessedValue == hiddenPhrase.charAt(inPhrase)){
               System.out.println();
               System.out.println(guessedValue + " Already Guessed.");
               numGuesses--;
               
               if(numGuesses > 0){
                  System.out.println("Remaining Lives: " + numGuesses);
                  }
               }   
            //Runs if user guesses a value correctly   
            else{
            
               System.out.println();
               System.out.println("Good Guess!");
               //Changes values from hidden phrase to correct guessed value
               while(inPhrase != -1){ 
                  correct--;
                  hiddenPhrase.setCharAt(inPhrase,guessedValue);//changes dash to correct value
                  inPhrase = phrase.indexOf(guessedValue, ++inPhrase);//iterates to the next value if there are duplicate values in phrase 
               }
               
            }
            System.out.println("Previous Guesses: " + alreadyGuessed);
            System.out.println();
         }
         //runs if user hits enter without typing a guess   
         catch(StringIndexOutOfBoundsException e){
            System.out.println("Enter a valid guess!");
            System.out.println();
            }
      }
         //runs if user runs out of guesses
         if(numGuesses == 0){
            System.out.println("You lose!");
            System.out.println("The hidden phrase is: " + phrase);
            }
         //runs if user guesses the phrase correctly  
         if(correct == 0){
            System.out.println(phrase);
            System.out.println();
            System.out.println("Congrats you guessed the phrase!");
            }
         
         playAgain();//jumps to play again method
         

   }//end guess
   
   //Method to play game again
   public static void playAgain(){
   
      Scanner keyboard = new Scanner(System.in);

      boolean repeat = true;//initiallizes boolean for loop
      
      //loops while repeat is true
      while(repeat){
      
         System.out.println("Do you want to play again?(y/n)");
         char again = keyboard.nextLine().charAt(0);//returns the first value of user input
         
         //runs if user wants to play again
         if(again == 'y' || again == 'Y'){
            repeat = false;
            System.out.println();
            System.out.println("Next Round!");
            System.out.println("***GUESSER LOOK AWAY FROM THE SCREEN***");
            System.out.println();
            playerTurn();//jumps to beginning of the game
            }
         
         //runs if user doesn't want to play again
         else if(again == 'n' || again == 'N'){
            System.out.println("Thanks for playing!");
            repeat = false;//ends loop
            }
         
         //runs if user enters invalid prompt
         else{
            System.out.println("Enter a valid command!");
            repeat = true;//continues loop
            }
      
         }
      }//end playAgain()
      
         
}//end class 
           
            
  