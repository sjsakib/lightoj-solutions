/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-10 10:19:10
 * Problem   : 1261 - K-SAT Problem
 * CPU       : 0.772
 * Memory    : 56860
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int m,n,k,p;
        int[][] mlist;
        mlist = new int[30][30];
        int[] sol = new int[30];
        boolean happy  = true;
        for(int i = 0;i<t;i++) {
            happy = false;
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
            
            for(int x = 0;x<n;x++) {
                for(int y = 0;y<k;y++) {
                    mlist[x][y] = in.nextInt();
                }
            }
            p = in.nextInt();
            for(int x = 0;x<p;x++) {
                sol[x] = in.nextInt();
            }
            
            for(int x = 0;x<n;x++) {
                for(int y = 0;y<k;y++) {
                    if(mlist[x][y]>0) {
                        if(inArray(mlist[x][y],sol)) {
                            happy = true;
                        }
                    } else {
                        if(!inArray(mlist[x][y]*-1,sol)) {
                            happy = true;
                        }
                    }
                }
                if(!happy) {
                    break;
                } else {
                    if(x!=(n-1)) {
                        happy = false;
                    }
                }
            }
            if(happy) {
                System.out.println("Case "+(i+1)+": Yes");
            } else {
                System.out.println("Case "+(i+1)+": No");
            }
            for(int x = 0;x<sol.length;x++) {
                sol[x] = 0;
            }
        }
    }
    static boolean inArray(int n,int[] array) {
        boolean in = false;
        for(int i = 0;i<array.length;i++) {
            if(n == array[i]) {
                in = true;
            }
        }
        return in;
    }
}
