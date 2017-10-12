/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-08 15:50:56
 * Problem   : 1227 - Boiled Eggs
 * CPU       : 0.324
 * Memory    : 23584
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int t = in.nextInt();
        int p,q,n,eggs,weight;
        int x;
        for(int i = 0;i<t;i++) {
            eggs = 0;
            weight = 0;
            n = in.nextInt();
            p = in.nextInt();
            q = in.nextInt();
            for(int j = 0;j<n;j++) {
                x = in.nextInt();
                if(weight+x<=q && eggs+1<=p) {
                    weight+=x;
                    eggs++;
                }
            }
            System.out.println("Case "+(i+1)+": "+eggs);
            
        }
    }
}
                
