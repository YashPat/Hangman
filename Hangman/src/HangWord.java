import java.util.*;

import static java.lang.System.*;

class HangWord
{
    private ArrayList<String> word = new ArrayList<>();
    private ArrayList<String> blank = new ArrayList<>();
    private ArrayList<String> numTried = new ArrayList<>();
    private int numTries = 7;
    private boolean lose = true;
    private String lettersTried = "";

    HangWord()
    {
    }

    boolean getLose()
    {
        return lose;
    }

    void setBlank()
    {
        for (int x = 0; x < word.size(); x++)
        {
            blank.add("-");
        }
    }

    String getBlank()
    {
        String blank1 = "";
        for (String aBlank : blank)
        {
            blank1 += aBlank;
        }
        return blank1;
    }

    private String getWord()
    {
        out.println();
        String word1 = "";
        for (String aWord : word)
        {
            word1 += aWord;
        }
        return word1;
    }

    void setWord(String w)
    {
        for (int x = 0; x < w.length(); x++)
        {
            word.add(w.charAt(x) + ""); //sets each location in the array equal to a letter of the word
        }
    }

    void checkLetter(String guess)
    {
        tried(guess);
        boolean attempt = false;
        out.println(guess);

        for (int x = 0; x < word.size(); x++)
        {
            out.print(word.get(x));
            if (guess.equalsIgnoreCase(word.get(x)))
            {
                PrettyScreen.playSound2();
                reveal(x, guess);
                attempt = true;
            }
        }
        if (!attempt)
        {
            numTries--;

            if (numTries > 0)
            {
                PrettyScreen.playSound2();
            }
            if (numTries == 0)
            {
                PrettyScreen.playSound3();
            }
        }
    }
    private void tried(String g)
    {
        String letters = "";
        numTried.add(g);
        for (int x = 0; x < numTried.size(); x++)
        {
            if (numTried != null)
            {
                letters += numTried.get(x)+ " ";
            }
        }
        lettersTried = letters;
    }

    void checkWin()
    {
        lose = !getWord().equalsIgnoreCase(getBlank());
    }

    private void reveal(int loc, String guess)
    {
        blank.set(loc, guess);
    }

    int getNumTries()
    {
        return numTries;
    }
    String getLettersTried()
    {
        return lettersTried;
    }

    String getCongratulations()
    {
        return "Congratulations! You have won!";
    }

    String getGameOver()
    {
        return "Game Over!";
    }
}
