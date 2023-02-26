import java.util.ArrayList;

public class Processor {
    private int charCount, palindromeCount, totalTokens, newLineCount, totalEmoticon, longestTokenSizes;
    private double averageTokenSize;
    private ArrayList<Integer> tokenSizes;
    private String string;


    public Processor(String string) {
        this.string = string;
        this.tokenSizes = new ArrayList<Integer>();
        this.processText();
        this.calculateAverageAndLongestTokenSizes();
    }

    public void processText() {
        String currentWord = "";
        String currentToken = "";
        boolean possibleEmoticon = false;
        for (int i = 0; i < string.length(); i++) {
            int character = string.charAt(i);
            if (possibleEmoticon) {
                this.checkIfEmoticon(character);
                possibleEmoticon = false;            
            }
            
            if (!Character.isWhitespace(character)) {
                if (Character.isAlphabetic(character)) {
                    currentWord += (char) character;
                }
                if ((char) character == ':') {
                    possibleEmoticon = true;
                }
                currentToken += (char) character;
                this.charCount++;
            } else {
                countNewLineChar(character);
                if (!currentToken.isBlank()) {
                    this.totalTokens++;
                    this.tokenSizes.add(currentToken.length());
                    currentToken = "";
                }
                if (!currentWord.isBlank()) {
                    this.checkIfPalindrome(currentWord);
                    currentWord = "";
                }
            }
        }
    }

    public void checkIfEmoticon(int character) {
        String emoticons = ")(DPOSCB3";

        if (emoticons.contains(String.valueOf((char) character))) {
            this.totalEmoticon++;
        }

    }

    private void countNewLineChar(int character) {
        if ((char) character == '\n') {
            this.newLineCount++;
        }
    }

    public boolean checkIfPalindrome(String word) {
        String rev = "";

        if (word.length() <= 1) {
            return false;
        }

        for (int i = word.length() - 1; i >= 0; i--) {
            rev += word.charAt(i);
        }

        if (word.equals(rev)) {
            this.palindromeCount++;
            return true;
        }

        return false;
    }

    public void calculateAverageAndLongestTokenSizes() {
        this.longestTokenSizes = Integer.MIN_VALUE;
        int total = 0;
        for (Integer tokenSize : this.tokenSizes) {
            if (tokenSize > this.longestTokenSizes) {
                this.longestTokenSizes = tokenSize;
            }
            total += tokenSize;
        }
        this.averageTokenSize = total / tokenSizes.size();
    }

    public int getCharCount() {
        return charCount;
    }

    public int getNewLineCount() {
        return newLineCount;
    }

    public int getPalindromeCount() {
        return palindromeCount;
    }

    public int getTotalEmoticon() {
        return totalEmoticon;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public double getAverageTokenSize() {
        return averageTokenSize;
    }
    
    public int getLongestTokenSizes() {
        return longestTokenSizes;
    }
}
