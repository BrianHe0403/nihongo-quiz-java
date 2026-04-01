import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Word {
    //build 2 attributes for class Word
    String japanese;    // japanese word
    String english;     // english meaning
    String category;    // word category
    // constructor : create a word with japanese and english meaning 
    public Word(String j, String e, String c){
        this.japanese = j;
        this.english = e;
        this.category = c;
    }
    public String getJapanese(){
        return japanese;
    }
    public String getEnglish(){
        return english;
    }
    public String getCategory(){
        return category;
    }
     @Override //to customize the toString method for better output readability} 
     public String toString(){
        return japanese + "," + english + "," + category ;
    }

    public static void removeByEnlish(ArrayList<Word> list,String target){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEnglish().equalsIgnoreCase(target)){
                String removeWord = list.get(i).getEnglish();
                list.remove(i);
                System.out.println(removeWord + " Removed!");
                return;
            } 
        }
        System.out.println("Word not found");
    }

    public static void searchByEnglish(ArrayList<Word> list,String target){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEnglish().equalsIgnoreCase(target) || list.get(i).getJapanese().equalsIgnoreCase(target)){
                list.get(i);
                System.out.println(list.get(i).getJapanese() + "," + list.get(i).getEnglish() + "," + list.get(i).getCategory());
                return;
            }
        }
        System.out.println("Word not found");
    }

    public static void saveBackToFile(ArrayList<Word> deck) throws IOException{
        PrintWriter out = new PrintWriter("words.txt");

        for(Word w : deck){
            out.println(w.japanese + "," + w.english + "," +w.category);
        }
        out.close();
    }
}

