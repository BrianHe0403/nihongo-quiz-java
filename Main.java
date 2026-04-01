import java.util.ArrayList;
import java.util.HashSet;
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
            String line = filScanner.nextLine().trim();
            String[] parts = line.split(",");

            if(parts.length ==3){
                String japanese = parts[0].trim();
                String english = parts[1].trim();
                String category = parts[2].trim();
                Word w = new Word(japanese,english,category);
                deck.add(w);
            }
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
            System.out.println("4. Delete a word");
            System.out.println("5. Search a word");
            System.out.println("6. Exit");
            System.out.println("==== Japanese Learning App ====");
            
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                // show all words
                case 1: //this loop goes through each word oject in deck during each iteration,w refers to the current object 
                        for(Word w : deck){
                            System.out.println(w);
                        }
                        break;
                // quiz mode
                case 2:
                    // select the category 
                    HashSet<String> categories = new HashSet<>();
                    for(Word w:deck){
                        categories.add(w.getCategory());
                    }
                    System.out.println("Available categoryies: ");
                    for(String s:categories){
                        System.out.println("- " + s);
                    }

                    System.out.println("Please select a category that you interested");
                    String categorySelect = sc.nextLine().trim();
                    
                    ArrayList<Word> categoryName = new ArrayList<>();
                    
                    for(Word w:deck){
                        if(w.getCategory().equalsIgnoreCase(categorySelect)){
                            categoryName.add(w);
                        }
                    }


                    // creat Arraylist object record the words that used 
                    ArrayList<Integer> used = new ArrayList<>();
                    // reset the miss file 
                    missed.clear(); 

                    int total = 10;
                    int correct = 0;
                    // Quiz loop
                    for (int i = 1; i <= total; i++){
                         // get random number
                        int index = rand.nextInt(categoryName.size());
                        // intend to not duplicate the word if 
                        while (used.contains(index)) {
                            index = rand.nextInt(categoryName.size());
                        }

                        used.add(index);
                        Word w = categoryName.get(index);
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
                            out.println(w);
                        }

                        out.close();
                        // show score
                        System.out.println();
                        System.out.println("Score: " + correct + " / " + total);
                        // missed word check and review
                        if(!missed.isEmpty()){
                            System.out.println("Missed Words:");
                            for (Word w : missed){
                                System.out.println(w);
                            }
                        }
                        break;
                // add new word
                case 3:
                        System.out.println("Enter a Japanese: ");
                        String newWordJapanese = sc.next();
                        System.out.println("Enter a English: ");
                        String newWordEnglish = sc.next();
                        System.out.println("Enter category: ");
                        String newCategory = sc.next();
                        Word neWord = new Word(newWordJapanese,newWordEnglish,newCategory);
                        deck.add(neWord);
                        // add new word to words file 
                        PrintWriter outAdd = new PrintWriter(new FileWriter("words.txt",true));
                        outAdd.println(neWord.japanese + "," + newWordEnglish + "," + newCategory + "\n");
                        outAdd.close();
                        break;

                // delete a word
                case 4:
                        System.out.println("Please enter the word you want delete in english: ");
                        String input = sc.nextLine().trim();
                        Word.removeByEnlish(deck,input);
                        Word.saveBackToFile(deck);
                        break;
                // search a word
                case 5:
                        System.out.println("Please enter the word(E/J) you want get(return in both english and japanese)");
                        String searchInput = sc.nextLine();
                        Word.searchByEnglish(deck, searchInput);
                        break;
                // Exit project
                case 6:
                        System.out.println("Thank you for using this app,ありがどう!");
                        running = false;
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
