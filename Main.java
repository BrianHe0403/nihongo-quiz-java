import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        
        ArrayList<Word> deck = new ArrayList<>(); 
        deck.add(new Word("いぬ","dog"));
        deck.add(new Word("ねこ","cat"));
        deck.add(new Word("ぞう","elephant"));
        deck.add(new Word("とり","bird"));
        deck.add(new Word("みず","water"));
        deck.add(new Word("きれい","beautiful"));

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int total = 5;
        int correct = 0;

        for (int i = 1; i <= total; i++){
            Word w = deck.get(rand.nextInt(deck.size()));
            System.out.println();
            System.out.println("Question " + i + " / " + total);
            System.out.println("Japanese: " + w.japanese);
            System.out.println("Meaning in English: ");

            String ans = sc.nextLine().trim();

            if(ans.equalsIgnoreCase(w.english)){
                System.out.println("Correct!");
                correct++;
            } else {
                System.out.println("Wrong Answer, Right answer is " + w.english);
            }
        }
        System.out.println();
        System.out.println("Score: " + correct + " / " + total);

        sc.close();

    }
}
