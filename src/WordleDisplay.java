import java.util.Scanner;

public class WordleDisplay {
    Wordle wordle;
    Scanner scanner;

    // ANSI escape codes used to produce color in the output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    public WordleDisplay(Wordle wordle, Scanner scanner) {
        this.wordle = wordle;
        this.scanner = scanner;
    }

    /**
     * This method prompts the player to guess the word given by the other player.
     */
    public void promptPlayerGuess() {
        while(true) {
            System.out.println(ANSI_YELLOW + "Guess the word:");
            String playerGuess = scanner.nextLine().toUpperCase();

            if (playerGuess.length() != wordle.getWord().length()) {
                System.out.println(ANSI_RED + "Please guess a 5-letter word:");
            } else {
                wordle.checkWordGuess(playerGuess);
                return;
            }
        }
    }

    public void printBoard() {
        printPlayerGuesses();
        printCleanBoard();
    }

    public void printCleanBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < wordle.getAttemptsRemaining(); i++) {
            for (int j = 0; j < wordle.getWord().length(); j++) {
                stringBuilder.append("_|");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            System.out.println(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
    }

    public void printPlayerGuesses() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : wordle.wordGuesses) {
            for (int i = 0; i < s.length(); i++) {
                String answer = wordle.getWord();
                char c = s.charAt(i);

                // letter is in correct place
                if (answer.charAt(i) == c) {
                    stringBuilder.append(ANSI_GREEN + c + ANSI_RESET);

                    // letter is in the answer but not in the right position
                } else if (answer.contains(Character.toString(c))) {
                    stringBuilder.append(ANSI_YELLOW + c + ANSI_RESET);
                } else {
                    stringBuilder.append(c);
                }
                stringBuilder.append("|");
            }

            stringBuilder.setLength((stringBuilder.length() - 1));
            System.out.println(stringBuilder);
            stringBuilder = new StringBuilder();
        }
    }
}
