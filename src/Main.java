import java.io.Console;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    /* This method prompts the player to input a 5-letter word for the second player
     * to guess.
     * Using console.readPassword() allows the player's guess to be hidden from the
     * second player.
     */
    public static String getPlayerWord() {
        Console console = System.console();
        // condition met if Main.java is run within IDE
        if (console == null) {
            System.out.println(ANSI_RED + "Could not get Console instance.");
            System.exit(0);
        }

        char[] wordArray = console.readPassword("Enter a 5-letter word: ");
        while (wordArray.length != 5) {
            System.out.println(ANSI_RED + "Word is not 5 letters, please try again: ");
            wordArray = console.readPassword();
        }
        return String.valueOf(wordArray).toUpperCase();
    }
    public static void main(String[] args) {
        String answer = getPlayerWord();

        Wordle wordle = new Wordle(answer);
        Scanner scanner = new Scanner(System.in);

        // prompts player to enter a word for the second player to guess
        WordleDisplay display = new WordleDisplay(wordle, scanner);
        while (!wordle.isGameOver()) {
            display.printBoard();
            display.promptPlayerGuess();
        }

        display.printBoard();

        if (wordle.playerGuessedWord()) {
            System.out.println(ANSI_GREEN + "Nicely done. You guessed the word!");
        } else {
            System.out.println(ANSI_RED + "Sorry, game over!");
            System.out.println("The answer was: " + wordle.getWord());
        }

        scanner.close();
    }
}
