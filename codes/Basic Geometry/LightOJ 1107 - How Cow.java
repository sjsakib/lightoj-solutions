/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-04 07:24:57
 * Problem   : 1107 - How Cow
 * CPU       : 0.572
 * Memory    : 29168
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t;
        t  = in.nextInt();
        int k;
        boolean inside;
        int x1,y1,x2,y2;
        int x,y;
        for(int i = 0;i<t;i++) {
            x1 = in.nextInt();
            y1 = in.nextInt();
            x2 = in.nextInt();
            y2 = in.nextInt();
            k = in.nextInt();
            System.out.print("Case ");
            System.out.print(i+1);
            System.out.print(":\n");
            for(int j = 0;j<k;j++) {
                x = in.nextInt();
                y = in.nextInt();
                if((x>x1 && x<x2) && (y>y1 && y<y2)) {
                    System.out.print("Yes\n");
                }else {
                    System.out.print("No\n");
                }
            }
        }
    }
}
