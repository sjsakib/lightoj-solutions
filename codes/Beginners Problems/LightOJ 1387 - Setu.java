/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-12 15:44:22
 * Problem   : 1387 - Setu
 * CPU       : 0.820
 * Memory    : 37556
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        String cmd;
        int n;
        int d;
        for(int i = 0;i<t;i++) {
            d = 0;
            n = in.nextInt();
            System.out.println("Case "+(i+1)+":");
            for(int k =0;k<n;k++) {
                cmd = in.next();
                if(cmd.compareTo("donate")==0) {
                    d+=in.nextInt();
                } else {
                    System.out.println(d);
                }
            }
        }
    }
}
