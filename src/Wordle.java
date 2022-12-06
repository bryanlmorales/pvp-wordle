import java.util.*;

public class Wordle {

    // ANSI escape codes used to produce color in the output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    private int userAttemptsRemaining;
    private int maxUserAttempts;
    private String word;
    List<String> wordGuesses;

    public Wordle(String word) {
        this.word = word;
        maxUserAttempts = 6;
        userAttemptsRemaining = maxUserAttempts;
        wordGuesses = new ArrayList<String>();
    }

    public void checkWordGuess(String str) {
        if (str.length() == word.length()) {
            wordGuesses.add(str);
            userAttemptsRemaining--;
        }
    }

    public boolean playerGuessedWord() { return wordGuesses.contains(word); }

    public boolean isGameOver () { return userAttemptsRemaining == 0 || playerGuessedWord(); }

    public int getAttemptsRemaining() {
        return userAttemptsRemaining;
    }

    public String getWord() {
        return word;
    }

}
