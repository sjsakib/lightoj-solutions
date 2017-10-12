/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-03 07:37:09
 * Problem   : 1053 - Higher Math
 * CPU       : 0.284
 * Memory    : 22796
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       
       int t,a,b,c;
       int aa,bb,cc;
       
       t = in.nextInt();
       for(int i = 0;i<t;i++) {
           a = in.nextInt();
           b = in.nextInt();
           c = in.nextInt();
           aa = a*a;
           bb = b*b;
           cc = c*c;
           if(aa+bb == cc || bb+cc == aa || cc+aa == bb) {
               System.out.printf("Case %d: yes\n",i+1);
           } else {
               System.out.printf("Case %d: no\n",i+1);
           }
       }
   }
}
