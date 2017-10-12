/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-08 15:22:52
 * Problem   : 1225 - Palindromic Numbers (II)
 * CPU       : 1.456
 * Memory    : 49968
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int t = in.nextInt();
        String x;
        boolean isPalindrome;
        int len;
        for(int i = 0;i<t;i++) {
            isPalindrome = true;
            x = in.next();
            len = x.length();
            for(int j = 0;j<len/2;j++) {
                if(x.charAt(j)!=x.charAt(len-1-j)) {
                    isPalindrome = false;
                    break;
                }
            }
            if(isPalindrome) {
                System.out.println("Case "+(i+1)+": "+"Yes");
            } else {
                System.out.println("Case "+(i+1)+": "+"No");
            }
        }
    }
}
