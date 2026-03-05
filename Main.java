import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //build vocabulay deck
        ArrayList<Word> deck = new ArrayList<>(); 
        File file = new File("words.txt");
        Scanner filScanner = new Scanner(file);

        while(filScanner.hasNextLine()){
            String line = filScanner.nextLine();
            String[] parts = line.split(",");
            Word w = new Word(parts[0],parts[1]);
            deck.add(w);
        }
        // creat two useful tool objects for getting the input and getting the random word
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int total = 5;
        int correct = 0;
        // creat Arraylist object track the missed words
        ArrayList<Word> missed = new ArrayList<>();
        // Quiz loop
        for (int i = 1; i <= total; i++){
            // get random number
            int index = rand.nextInt(deck.size());
            // intend to not duplicate the word if 
            Word w = deck.remove(index); // this code basicly did 2 things  1. remove the object at index from deck
                                                                   //       2. return the object at index assigned to w
            System.out.println();   
            System.out.println("Question " + i + " / " + total);
            System.out.println("Japanese: " + w.japanese);
            System.out.print("Meaning in English: ");

            String ans = sc.nextLine().trim();

            if(ans.equalsIgnoreCase(w.english)){
                System.out.println("Correct!");
                correct++;
            } else {
                System.out.println("Wrong Answer, Right answer is " + w.english);
                missed.add(w);
            }
        }
        // create a wrong_word file 
        PrintWriter out = new PrintWriter("Wrong_words.txt");
        for(Word w : missed){
            out.println(w.japanese + " -> " + w.english);
        }

        out.close();
        // show score
        System.out.println();
        System.out.println("Score: " + correct + " / " + total);
        // missed word check and review
        if(!missed.isEmpty()){
            System.out.println("Missed Words:");
            for (Word w : missed){
                System.out.println(w.japanese + " -> " + w.english);
            }
        }
        //close the stream and releases system resources
        sc.close();

    }
}
