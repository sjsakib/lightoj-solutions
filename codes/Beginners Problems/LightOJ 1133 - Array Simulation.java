/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-04 09:55:11
 * Problem   : 1133 - Array Simulation
 * CPU       : 0.908
 * Memory    : 47200
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int t;
        t = in.nextInt();
        int n,m;
        int x,y;
        int l;
        char c = '\0';
        for(int i = 0;i<t;i++) {
            n = in.nextInt();
            m = in.nextInt();
            int[] el = new int[n];
            for(int j =0;j<n;j++) {
                el[j] = in.nextInt();
            }
            for(int j = 0;j<m;j++) {
                c = in.next().charAt(0);
                switch(c) {
                    case 'S':
                        x = in.nextInt();
                        for(int k = 0;k<el.length;k++) {
                            el[k]+=x;
                        }
                        break;
                    case 'M':
                        x = in.nextInt();
                        for(int k = 0;k<el.length;k++) {
                            el[k]*=x;
                        }
                        break;
                    case 'D':
                        x = in.nextInt();
                        for(int k = 0;k<el.length;k++) {
                            el[k]/=x;
                        }
                        break;
                    case 'R':
                        l = el.length-1;
                        for(int k = 0;k<el.length/2;k++) {
                            y = el[k];
                            el[k] = el[l];
                            el[l] = y;
                            l--;
                        }
                        break;
                    case 'P':
                        x = in.nextInt();
                        y = in.nextInt();
                        l = el[x];
                        el[x] = el[y];
                        el[y] = l;
                    }
                }
                System.out.printf("Case %d:\n",i+1);
                int k;
                for(k = 0;k<el.length-1;k++) {
                    System.out.print(el[k]);
                    System.out.print(" ");
                } 
                System.out.println(el[k]);
            }
        }
    }
