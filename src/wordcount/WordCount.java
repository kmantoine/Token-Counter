package wordcount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;

public class WordCount {
    Map<String, Integer> wordCounter = new HashMap<>(); 
    
    public static void main(String[] args) throws IOException{
        File inputFile = new File("files\\test.txt");
        WordCount obj = new WordCount();
        obj.mapper(inputFile);  
        obj.reducer();
    }
    
    public void mapper(File inputText) throws IOException{
        Scanner input = new Scanner (new FileInputStream (inputText));
        int counter=1;
        String token;       
        
        while (input.hasNext()){
            token = input.next().replaceAll("[^a-zA-Z-/]", "");
            if (wordCounter.containsKey(token)){
                wordCounter.put(token, wordCounter.get(token) + counter);
            }
            else {
                wordCounter.put(token, counter);
            }
        }
    }
    
    public void reducer() throws IOException{
        BufferedWriter writer = null;
        writer= new BufferedWriter (new FileWriter (new File ("files\\output.txt")));
        for (String key:wordCounter.keySet()){
            writer.append(key + " - " + wordCounter.get(key) + "\n");
        }
        writer.close();
    }
}
