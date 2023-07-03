import java.util.*;
public class task1_codsoft
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int score = 0;
        System.out.println("Welcome to the Number Guessing Game!");
        boolean playAgain = true;
        while (playAgain) 
        {
            int generatedNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("A random number between " + minRange + " and " + maxRange + " has been generated.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            while (attempts < maxAttempts) 
            {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == generatedNumber) 
                {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    break;
                } 
                else if (userGuess < generatedNumber)
                {
                    System.out.println("Too low! Try again.");
                } 
                else 
                {
                    System.out.println("Too high! Try again.");
                }
            }
            if (!guessedCorrectly) 
            {
                System.out.println("You have used all your attempts. The correct number was: " + generatedNumber);
            }
            score += guessedCorrectly ? 1 : 0;
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = sc.next().toLowerCase();
            playAgain = playAgainInput.equals("yes") || playAgainInput.equals("y");
        }
        System.out.println("Game over. Your score is: " + score);
    }
}