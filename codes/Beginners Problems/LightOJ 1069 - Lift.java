/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-03 07:55:45
 * Problem   : 1069 - Lift
 * CPU       : 0.212
 * Memory    : 21656
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t,l,m,r;
        
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
            m = in.nextInt();
            l = in.nextInt();
            
            r = (Math.abs(l-m)+m)*4+19;
            System.out.printf("Case %d: %d\n",i+1,r);
        }
    }
}
