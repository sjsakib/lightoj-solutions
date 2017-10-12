/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-04-12 00:26:12
 * Problem   : 1006 - Hex-a-bonacci
 * CPU       : 0.340
 * Memory    : 27276
**/
import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner s  = new Scanner(System.in);
		int n, caseno = 0, cases;
		cases = s.nextInt();
		while( cases>0 ) {
			int serial[];
			serial = new int[10001];
			for(int i = 0;i<6;i++) {
				serial[i] = s.nextInt() % 10000007;
			}
			n = s.nextInt();
			for(int i = 6;i<=n;i++) {
				serial[i] = (serial[i-1]+serial[i-2]+serial[i-3]+serial[i-4]+serial[i-5]+serial[i-6]) % 10000007;
			}
			System.out.printf("Case %d: %d\n", ++caseno, serial[n]);
			cases--;
		}
	}
}
