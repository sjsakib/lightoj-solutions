/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-08 14:56:09
 * Problem   : 1216 - Juice in the Glass
 * CPU       : 0.288
 * Memory    : 23204
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int t = in.nextInt();
        double r1,r2,h,p;
        double large,small;
        double v;
        for(int i = 0;i<t;i++) {
            r1 = in.nextDouble();
            r2 = in.nextDouble();
            h = in.nextDouble();
            p = in.nextDouble();
            
            r1 = (((r1-r2)/h)*p)+r2;
            
            v = (Math.PI*r2*r2*p)+ (Math.PI*r2*(r1-r2)*p) + ((Math.PI*(r1-r2)*(r1-r2)*p)/3.0);
            
            System.out.println("Case "+(i+1)+": "+v);
        }
    }
}