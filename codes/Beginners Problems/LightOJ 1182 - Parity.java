/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-15 08:35:56
 * Problem   : 1182 - Parity
 * CPU       : 0.488
 * Memory    : 25804
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t,n,c;
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
            n = in.nextInt();
            c = Integer.bitCount(n);
            if(c%2 == 0 ) {
                System.out.printf("Case %d: even\n",i+1);
            } else {
                System.out.printf("Case %d: odd\n",i+1);
            }
        }
    }
}
