package tiny_scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author GHAMRY and MOSTAFA
 */
public class TINY_Scanner {

    public static void main(String[] args) {
        String str = "";
        try {
            str = readFile("Input.txt");
        } catch(Exception e) {
            System.out.println("Cannot read from text file");
        }
        
        List<String> ReservedWord = new ArrayList<String>();
        List<String> Symbol = new ArrayList<String>();
        
        ReservedWord.addAll(Arrays.asList("if", "then", "else", "end", "repeat", "until", "read", "write"));
        Symbol.addAll(Arrays.asList("+", "-", "*", "/", "=", "<", "(", ")", ";", ":="));
        
        String regex = "((?<=[\\*\\+-/=<();:={}\n \t ])|(?=[\\*\\+-/=<();:={}\n\t ]))";
        String[] parts = str.split(regex);

        for (int i = 0; i < parts.length; i++) {
            if(ReservedWord.contains(parts[i])) {
                System.out.print(parts[i]);
                printTokenClass("Reserved word");
            }
            else if(Symbol.contains(parts[i])) {
                System.out.print(parts[i]);
                printTokenClass("Symbol");
            }
            else if(isBlank(parts[i])) {
                continue;
                //System.out.print(parts[i]);
                //printTokenClass("White Space");
            }
            else if(parts[i].charAt(0) == '{'){
                do{
                    System.out.print(parts[i] + " ");
                    i++;
                } while(parts[i].charAt(parts[i].length()-1) != '}');
                System.out.print(parts[i]);
                printTokenClass("Comment");
            }
            else if(parts[i].contains(":")) {
                if (parts[i+1].contains("=")) {
                    System.out.print(parts[i] + parts[i+1]);
                    printTokenClass("Symbol");
                    i++;
                }
                else {
                    System.out.print(parts[i]);
                    printTokenClass("Symbol");
                }
            }
            else if(isNum(parts[i])) {
                System.out.print(parts[i]);
                printTokenClass("Number");
            }
            else {
                System.out.print(parts[i]);
                printTokenClass("Identifier");
            }
        }
    }
    
    public static void printTokenClass(String tokenClass){
        System.out.println(String.format("%1$30s", tokenClass));
    }
    
    public static boolean isBlank(String value) {
        return (value == " " || value == null || value.equals("") || value.equals("null") || value.trim().equals(""));
    }
    
    public static boolean isNum(String value) {
        boolean ret = false;
        if (!isBlank(value)) {
            ret = value.matches("^[0-9]+$");
        }
        return ret;
    }
    
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        
        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
