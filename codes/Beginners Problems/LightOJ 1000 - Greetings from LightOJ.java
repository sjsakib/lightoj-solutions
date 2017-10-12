/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-04-07 00:59:15
 * Problem   : 1000 - Greetings from LightOJ
 * CPU       : 0.268
 * Memory    : 22468
**/
import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int t,a,b;
		t = s.nextInt();
		
		for(int i = 1;i<=t;i++) {
			a = s.nextInt();
			b  = s.nextInt();
			System.out.printf("Case %d: %d\n",i,a+b);
		}
	}
}
	
