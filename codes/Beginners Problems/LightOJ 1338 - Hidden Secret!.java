/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-12 14:35:50
 * Problem   : 1338 - Hidden Secret!
 * CPU       : 0.352
 * Memory    : 29536
**/
import java.util.Scanner;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        in.nextLine();
        String s1,s2;
        char c;
        List<Character> c1 = new ArrayList<Character>();
        List<Character> c2 = new ArrayList<Character>();
        Iterator<Character> it1,it2;
        Character char1,char2;
        for(int i = 0;i<t;i++) {
            s1 = in.nextLine();
            s2 = in.nextLine();

            for(int k = 0;k<s1.length();k++) {
                c = s1.charAt(k);
                if(c!=' ') {
                    if(Character.isUpperCase(c)) {
                        c1.add(new Character(Character.toLowerCase(c)));
                    } else {
                        c1.add(new Character(c));
                    }
                }
            }
            for(int k = 0;k<s2.length();k++) {
                c = s2.charAt(k);
                if(Character.isLetter(c)) {
                    if(Character.isUpperCase(c)) {
                        c2.add(new Character(Character.toLowerCase(c)));
                    } else {
                        c2.add(new Character(c));
                    }
                }
            }
            it1 = c1.iterator();
            it2 = c2.iterator();
            while(it1.hasNext()) {
                char1 = it1.next();
                while(it2.hasNext()){
                    char2 = it2.next();
                    if(char1.charValue() == char2.charValue()) {
                        it1.remove();
                        it2.remove();
                        break;
                    }
                }
                it2 = c2.iterator();
            }
            if(c1.isEmpty() || c2.isEmpty()) {
                System.out.println("Case "+(i+1)+": Yes");
            } else {
                System.out.println("Case "+(i+1)+": No");
            }
            c1.clear();
            c2.clear();
        }
    }
}
