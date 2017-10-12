/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-12 15:23:53
 * Problem   : 1354 - IP Checking
 * CPU       : 0.276
 * Memory    : 22108
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        String s1,s2;
        String[] ip1,ip2;
        boolean same;
        for(int i = 0;i<t;i++) {
            same = true;
            s1 = in.next();
            s2 = in.next();
            ip1 = s1.split("\\.");
            ip2 = s2.split("\\.");

            for(int k = 0;k<4;k++) {
                if(Integer.parseInt(ip1[k])!=Integer.parseInt(ip2[k],2)) {
                    same = false;
                }
            }
            if(same) {
                System.out.println("Case "+(i+1)+": Yes");
            } else {
                System.out.println("Case "+(i+1)+": No");
            }
        }
    }
}
