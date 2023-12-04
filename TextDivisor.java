import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TextDivisor {

    private String file;
    private int nbMap;
    public TextDivisor(String file,int nbMap) {
        this.file = file;
        this.nbMap = nbMap;
    }

    public String textReader(){
        try {
            String textFromFile = Files.readString(Paths.get(this.file));
            return textFromFile;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }
    }
    public ArrayList<String> textToArray() {
        String text = textReader();
        String[] words = text.split("[\\p{Punct}\\s]+");
        ArrayList<String> res = new ArrayList<>(Arrays.asList(words));
        return res;
    }

    public ArrayList<ArrayList<String>> textDivision() {
        ArrayList<String> allWords = textToArray();
        int nbWords = (int) allWords.size()/nbMap;
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(int i = 0; i < this.nbMap; i++){
            if(i != this.nbMap-1) {
                ArrayList<String> subList = new ArrayList<>(allWords.subList(i * nbWords, ((i + 1) * nbWords) - 1));
                res.add(subList);
            } else {
                ArrayList<String> subList = new ArrayList<>(allWords.subList(i * nbWords, allWords.size()));
                res.add(subList);
            }
        }
        return res;
    }



}
