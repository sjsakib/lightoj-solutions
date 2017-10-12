/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-08 20:29:49
 * Problem   : 1241 - Pinocchio
 * CPU       : 0.280
 * Memory    : 22684
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int t = in.nextInt();
        int n;
        int fal;
        int x1,x2,dx;
        
        for(int i = 0;i<t;i++) {
            fal = 0;
            n = in.nextInt();
            x1 = 2;
            for(int j =0;j<n;j++) {
                x2 = in.nextInt();
                dx = x2 - x1;
                if(dx>5) {
                    if(dx%5==0) {
                        fal+=(dx/5);
                    } else {
                        fal+=((dx/5)+1);
                    }
                } else if(dx>0) {
                    fal++;
                }
                x1 = x2;
            }
            System.out.println("Case "+(i+1)+": "+fal);
        }
    }
}
