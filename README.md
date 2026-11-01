# Jordle

Jordle narrows down Wordle answers as you play. After each guess, it checks every possible five-letter word against the colors you've seen and recommends the next best guess sorted by letter frequency.

## MVP

Enter a guess and click the tiles to set their colors. The program will filter a five-letter word list to the words that still match and suggests the next best guess. It runs in a Swing window.

## Future plans

- Pick guesses that eliminate about half of the remaining words each turn
- Separate answer and allowed-guess lists
- Display the remaining word count and remaining letters
- Undo guesses

## Tech stack

Java (JDK 25) and Swing. The word list is the public-domain ENABLE list in `data/words.txt`. The Eisenhower matrix and system diagram are in `docs/`.