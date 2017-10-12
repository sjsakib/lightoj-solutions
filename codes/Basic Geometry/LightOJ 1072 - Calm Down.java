/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-03 09:01:58
 * Problem   : 1072 - Calm Down
 * CPU       : 0.284
 * Memory    : 22904
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t;
        double R,r,theta;
        double n;
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
            R = in.nextDouble();
            n = in.nextDouble();
            theta = (2*Math.PI/n)/2.0;
            r = (R*Math.sin(theta))/(1+Math.sin(theta));
            System.out.print("Case ");
            System.out.print(i+1);
            System.out.print(": ");
            System.out.println(r);
               
        }
    }
}
