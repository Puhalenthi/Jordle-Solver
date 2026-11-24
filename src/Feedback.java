public class Feedback {

    private final String guess;
    private final LetterColor[] colors;

    public Feedback(String guess, LetterColor[] colors) {
        this.guess = guess;
        this.colors = colors;
    }

    /**
     * Converts a guess string into an array of LetterColors
     */
    public static Feedback fromInput(String guess, String pattern) {
        LetterColor[] colors = new LetterColor[5];
        for (int i = 0; i < 5; i++) {
            char c = pattern.charAt(i);
            if (c == 'g') {
                colors[i] = LetterColor.GREEN;
            } else if (c == 'y') {
                colors[i] = LetterColor.YELLOW;
            } else if (c == 'x') {
                colors[i] = LetterColor.GREY;
            }
        }

        return new Feedback(guess, colors);
    }

    public String getGuess() {
        return guess;
    }

    public LetterColor getColor(int index) {
        return colors[index];
    }

    /**
     * Outputs a string that indicates whether a letter was gray, yellow, or green.
     * Returns the full status of each letter of the guess
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < guess.length(); i++) {
            result += guess.charAt(i) + "=" + colors[i] + " ";
        }
        return result.toString().trim();
    }
}