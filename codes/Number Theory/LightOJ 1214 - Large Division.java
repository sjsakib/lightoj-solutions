/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-08 00:01:18
 * Problem   : 1214 - Large Division
 * CPU       : 0.592
 * Memory    : 35768
**/
import java.util.Scanner;
import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        BigInteger a;
        BigInteger mod;
        BigInteger b;
        
        for(int i = 0;i<t;i++) {
            a = in.nextBigInteger();
            b = in.nextBigInteger();
            b = b.abs();
            
            mod = a.mod(b);
            
            if(mod.compareTo(BigInteger.ZERO) == 0) {
                System.out.println("Case "+(i+1)+": "+"divisible");
            } else {
                System.out.println("Case "+(i+1)+": "+"not divisible");
            }
        }
        
    }
}
