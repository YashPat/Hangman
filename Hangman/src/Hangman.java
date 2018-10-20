import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class Hangman    //Driver
{
    public static void main(String[] args) throws IOException
    {
        //definitions
        String word; // word that the program picks and passes down to the HangWord class
        //*****Reads words from data file****
        Scanner scan = new Scanner(new File("words.data"));
        int numInFile = scan.nextInt();

        ArrayList<String> words = new ArrayList<>(); //array that holds all the words so the random int can pick a word
        int random = (int) (Math.random() * ((numInFile - 1) + 1) + 1); //random number picks the word
        //input
        for (int x = 0; x <= numInFile; x++)
        {
            words.add(scan.nextLine());
        }

        //picks word
        word = words.get(random);

        out.println(word);

        new PrettyScreen(word);

    }
}

