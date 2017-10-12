/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-11 13:48:16
 * Problem   : 1311 - Unlucky Bird
 * CPU       : 0.332
 * Memory    : 23900
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int t = in.nextInt();
    double v1,v2,v,a1,a2;
    double d,s,t1,t2;
    for(int i = 0;i<t;i++) {
        v1 = in.nextDouble();
        v2 = in.nextDouble();
        v = in.nextDouble();
        a1 = in.nextDouble();
        a2 = in.nextDouble();

        d  = ((v1*v1)/(2*a1))+((v2*v2)/(2*a2));
        t1 = v1/a1;
        t2 = v2/a2;
        if(t1>t2) {
            s = t1*v;
        } else {
            s = t2*v;
        }

        System.out.println("Case "+(i+1)+": "+d+" "+s);
    }
}
}
