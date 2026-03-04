import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        //build vocabulay deck
        ArrayList<Word> deck = new ArrayList<>(); 
        deck.add(new Word("いぬ","dog"));
        deck.add(new Word("ねこ","cat"));
        deck.add(new Word("ぞう","elephant"));
        deck.add(new Word("とり","bird"));
        deck.add(new Word("みず","water"));
        deck.add(new Word("きれい","beautiful"));
        // creat two useful tool objects for getting the input and getting the random word
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int total = 5;
        int correct = 0;
        // creat Arraylist object track the missed words
        ArrayList<Word> missed = new ArrayList<>();
        // Quiz loop
        for (int i = 1; i <= total; i++){
            Word w = deck.get(rand.nextInt(deck.size()));
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
        //close input flow
        sc.close();

    }
}
