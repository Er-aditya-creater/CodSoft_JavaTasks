import java.util.Random;
import java.util.Scanner;

public class NumberGame_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int score = 0;

        boolean playAgain = true;
        while (playAgain) {
            int generatedNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have generated a number between " + minRange + " and " + maxRange + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            int attempts = 0;
            boolean guessedCorrectly = false;
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    System.out.println("Number of attempts: " + attempts);
                    guessedCorrectly = true;
                    score++;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Oops! You ran out of attempts.");
                System.out.println("The number was: " + generatedNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = sc.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes") || playAgainResponse.equals("y");
        }

        System.out.println("Your score: " + score);
        System.out.println("Thank you for playing the Number Guessing Game!");
        sc.close();
    }

}