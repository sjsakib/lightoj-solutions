/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-03 06:48:39
 * Problem   : 1042 - Secret Origins
 * CPU       : 0.236
 * Memory    : 21812
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t,n;
        int[] bin = new int[32];
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
          n = in.nextInt();

          int j;
          for(j= 0;n>0;j++) {
              bin[j] = n%2;
              n = n/2;
          }
          bin[j] = -1;

          rearrange(bin);

          n = 0;
          for(j = 0;bin[j]!=-1;j++) {
              n+=bin[j]*Math.pow(2,j);
          }
          System.out.printf("Case %d: %d\n",i+1,n); 
      }
    }

    static void rearrange(int[] bin) {
        int found = 0;
        int i;
        for(i = 0;;i++) {
            if(bin[i] == 1) {
                found++;
            } else if(found>0 && (bin[i] == 0 || bin[i] == -1)) {
                if(bin[i] == -1) bin[i+1] = -1;
                bin[i] = 1;
                for(int k = i-1;k>=0;k--) {
                    bin[k] = 0;
                }
                for(int k = 0;k<found-1;k++) {
                    bin[k] = 1;
                }
                break;
            }
            
        }
    }
    
}
