/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-22 17:41:23
 * Problem   : 1433 - Minimum Arc Distance
 * CPU       : 0.268
 * Memory    : 22452
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Point o = new Point(0,0);
        Point a = new Point(0,0);
        Point b = new Point(0,0);
        double theta;
        double oa,ab;
        double s;
        for(int i = 0;i<t;i++) {
            o.x = in.nextInt();
            o.y = in.nextInt();
            a.x = in.nextInt();
            a.y = in.nextInt();
            b.x = in.nextInt();
            b.y = in.nextInt();

            oa = o.dis(a);
            ab = a.dis(b);
            theta = Math.acos(( 2*oa*oa - ab*ab)/(2*oa*oa));
            s = theta*oa;
            if(oa == 0) {
                s = 0;
            }
            System.out.println("Case "+(i+1)+": "+s);
            
        }
    }
}

class Point {
    double x;
    double y;

    Point(double x_,double y_) {
        x = x_;
        y = y_;
    }
    double dis(Point p) {
        return Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
    }
}
