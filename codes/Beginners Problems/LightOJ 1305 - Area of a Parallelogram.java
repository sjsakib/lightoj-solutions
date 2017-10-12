/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-11 12:06:48
 * Problem   : 1305 - Area of a Parallelogram
 * CPU       : 0.564
 * Memory    : 29848
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        Point a,b,c,d;
        int area;
        a = new Point(0,0);
        b = new Point(0,0);
        c = new Point(0,0);
        d = new Point(0,0);

        for(int i = 0;i<t;i++) {
            a.x = in.nextInt();
            a.y = in.nextInt();
            b.x = in.nextInt();
            b.y = in.nextInt();
            c.x = in.nextInt();
            c.y = in.nextInt();

            d.x = a.x+(c.x-b.x);
            d.y = a.y+(c.y-b.y);

            area = Math.abs(((a.x*b.y+b.x*c.y+c.x*d.y+d.x*a.y)-(a.y*b.x+b.y*c.x+c.y*d.x+d.y*a.x))/2);
            System.out.println("Case "+(i+1)+": "+d.x+" "+d.y+" "+area);
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x_,int y_) {
        x = x_;
        y = y_;
    }
}
