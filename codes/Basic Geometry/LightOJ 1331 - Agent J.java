/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-12 14:34:29
 * Problem   : 1331 - Agent J
 * CPU       : 0.792
 * Memory    : 42976
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        double r1,r2,r3;
        double a,b,c;
        double an,bn,cn;
        double s,area;
        for(int i = 0;i<t;i++) {
            r1 = in.nextDouble();
            r2 = in.nextDouble();
            r3 = in.nextDouble();

            c = r1+r2;
            b = r2+r3;
            a = r1+r3;
            an = Math.acos((b*b+c*c-a*a)/(2*b*c));
            bn = Math.acos((a*a+c*c-b*b)/(2*a*c));
            cn = Math.acos((b*b+a*a-c*c)/(2*b*a));
            s = (a+b+c)/2;
            area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
            area-=(((r1*r1*bn)/2)+((r3*r3*cn)/2)+((r2*r2*an)/2));
            System.out.println("Case "+(i+1)+": "+area);
        }
    }
}
