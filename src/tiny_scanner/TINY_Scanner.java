package tiny_scanner;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author GHAMRY and ZUKI
 */
public class TINY_Scanner {

    public static void main(String[] args) {
        try {
            //FileReader test = new FileReader(TINY_Scanner.class.getResource("input.txt"));
        } catch(Exception e) {
            
        }
        
        String str = "read x;\n" +
            "if 0 < x then {this is a comment}\n" +
            "fact := 1;\n" +
            "repeat \n" +
            "fact := fact * x;\n" +
            "x := x - 1\n" +
            "until x = 0;\n" +
            "write fact\n" +
            "end";
        
        List<String> ReservedWord = new ArrayList<String>();
        List<String> Symbol = new ArrayList<String>();
        
        ReservedWord.addAll(Arrays.asList("if", "then", "else", "end", "repeat", "until", "read", "write"));
        Symbol.addAll(Arrays.asList("+", "-", "*", "/", "=", "<", "(", ")", ";", ":="));
        
        //String regex = "((?<=[\\*\\+-/=<();:={}\n \t ])|(?=[\\*\\+-/=<();:={}\n\t ]))";
        String[] parts = str.split(" ");
        
        for (int i = 0; i < parts.length; i++) {
            if(ReservedWord.contains(parts[i])) {
                System.out.println(parts[i] + "     Reserved Word");
            }
            else if(Symbol.contains(parts[i])) {
                System.out.println(parts[i] + "     Symbol");
            }
            else if(isNum(parts[i])) {
                System.out.println(parts[i] + "     Number");
            }
            else if(isBlank(parts[i])) {
                System.out.println(parts[i] + "     White Space");
            }
            else {
                System.out.println(parts[i] + "     Identifier");
            }
            //comment
            //x;
        }
        
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
    
}
