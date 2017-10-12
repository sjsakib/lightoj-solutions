/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-05-30 07:04:42
 * Problem   : 1022 - Circle in Square
 * CPU       : 0.756
 * Memory    : 33556
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t;
        double r,x;
        double ar1;
        double ar2;
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
            r = in.nextDouble();
            ar1 = 4*r*r;
            ar2 = 2*Math.acos(0.0)*r*r;
            x = ar1 - ar2;
            System.out.printf("Case %d: %.2f\n",i+1,x);
        }
    }
}
