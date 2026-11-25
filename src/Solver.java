import java.util.ArrayList;
import java.util.List;

public class Solver {
    private List<String> candidates;

    public Solver(List<String> words) {
        candidates = new ArrayList<>(words);
    }

    /**
     * Updates the candidates list to reflect the feedback of the current guess
     */
    public void getFeedback(Feedback feedback) {
        List<String> remaining = new ArrayList<>();
        for (String word : candidates) {
            if (isMatching(word, feedback)) {
                remaining.add(word);
            }
        }
        candidates = remaining;
    }

    /**
     * Checks to see if word could be the answer given the current feedback
     */
    public static boolean isMatching(String word, Feedback feedback) {
        LetterColor[] colors = computeColors(feedback.getGuess(), word);
        for (int i = 0; i < 5; i++) {
            if (colors[i] != feedback.getColor(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the colors of the guess
     */
    public static LetterColor[] computeColors(String guess, String answer) {
        LetterColor[] colors = new LetterColor[5];
        int[] letterCounts = new int[26];

        // Marks green if the color is in the right position and update the frequency
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                colors[i] = LetterColor.GREEN;
            } else {
                letterCounts[answer.charAt(i) - 'a']++;
            }
        }

        // Marks yellow if unused copies of the word remain, else marks grey
        for (int i = 0; i < 5; i++) {
            if (colors[i] == null) {
                int index = guess.charAt(i) - 'a';

                if (letterCounts[index] > 0) {
                    colors[i] = LetterColor.YELLOW;
                    letterCounts[index]--;
                } else {
                    colors[i] = LetterColor.GREY;
                }
            }
        }
        return colors;
    }
    
    /**
     * Suggests the remaining word whose letters are most common among the candidates
     */
    public String suggestGuess() {
        int[] letterCounts = new int[26];
        for (String word : candidates) {
            for (int i = 0; i < 26; i++) {
                char letter = (char) ('a' + i);
                if (word.indexOf(letter) != -1)
                    letterCounts[i]++;
            }
        }

        String bestWord = "";
        int bestScore = -1;
        for (String word : candidates) {
            int score = 0;
            for (int i = 0; i < 26; i++) {
                char letter = (char) ('a' + i);
                if (word.indexOf(letter) != -1)
                    score += letterCounts[i];
            }
            if (score > bestScore) {
                bestScore = score;
                bestWord = word;
            }
        }
        return bestWord;
    }

    public List<String> getCandidates() {
        return candidates;
    }
}