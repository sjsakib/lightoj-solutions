/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-07 10:07:57
 * Problem   : 1211 - Intersection of Cubes
 * CPU       : 0.748
 * Memory    : 56672
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        int xx,yy,zz,xx2,yy2,zz2;
        int x,y,z,x2,y2,z2;
        int n;
        int v;
        for(int i=0;i<t;i++) {
            n = in.nextInt();
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            x2 = in.nextInt();
            y2 = in.nextInt();
            z2 = in.nextInt();
            for(int j = 1;j<n;j++) {
                xx = in.nextInt();
                yy = in.nextInt();
                zz = in.nextInt();
                xx2 = in.nextInt();
                yy2 = in.nextInt();
                zz2 = in.nextInt();
                
                if(  (x>=xx2) || (x2<=xx) || (y>=yy2) || (y2<=yy) || (z>=zz2) || (z2<=zz) ) {
                    x = 0;
                    x2 = 0;
                    break;
                }
                
                if(x<xx) {
                    x = xx;
                }
                if(y<yy) {
                    y = yy;
                }
                if(z<zz) {
                    z = zz;
                }
                if(x2>xx2) {
                    x2 = xx2;
                } 
                if(y2>yy2) {
                    y2 = yy2;
                }
                if(z2>zz2) {
                    z2 = zz2;
                }
            }
            x = Math.abs(x-x2);
            y = Math.abs(y-y2);
            z = Math.abs(z-z2);
            
            v = x*y*z;
            System.out.printf("Case %d: %d\n",i+1,v);
        }
    }
}
