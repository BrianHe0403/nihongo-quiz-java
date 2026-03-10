import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        //build vocabulay deck
        ArrayList<Word> deck = new ArrayList<>(); 
        // creat Arraylist object track the missed words
        ArrayList<Word> missed = new ArrayList<>();

        File file = new File("words.txt");
        Scanner filScanner = new Scanner(file);

        while(filScanner.hasNextLine()){
            String line = filScanner.nextLine();
            String[] parts = line.split(",");
            Word w = new Word(parts[0],parts[1]);
            deck.add(w);
        }
        // creat a tool object Scanner to read input from user 
        Scanner sc = new Scanner(System.in);

         // creat a useful tool object getting the random word
        Random rand = new Random();

         // build menu
        boolean running = true;
        while(running){
            System.out.println("\n==== Japanese Learning App ====");
            System.out.println("1. Show all words");
            System.out.println("2. Quiz mode");
            System.out.println("3. Add a new word");
            System.out.println("4. Remove a word");
            System.out.println("5. Search a word");
            System.out.println("6. Exit");
            System.out.println("==== Japanese Learning App ====");
            
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                // show all words
                case 1: //this loop goes through each word oject in deck during each iteration,w refers to the current object 
                        for(Word w : deck){
                            System.out.println(w.japanese + " --- " + w.english);
                        }
                        break;
                // quiz mode
                case 2:
                    int total = 5;
                    int correct = 0;
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
                        break;
                // add new word
                case 3:
                        System.out.println("Enter a Japanese: ");
                        String newWordJapanese = sc.next();
                        System.out.println("Enter a English: ");
                        String newWordEnglish = sc.next();
                        Word neWord = new Word(newWordJapanese,newWordEnglish);
                        deck.add(neWord);
                        // add new word to words file 
                        PrintWriter outAdd = new PrintWriter(new FileWriter("words.txt",true));
                        outAdd.println(neWord.japanese + "," + newWordEnglish);
                        outAdd.close();
                        break;


                        
                default:
                        System.out.println("Invild choice please enter a number 1-6");
                break;
                            }
            }

            //close the stream and releases system resources
                sc.close();
                filScanner.close();
    }
}
