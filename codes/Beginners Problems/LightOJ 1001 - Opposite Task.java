/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-04-10 09:25:26
 * Problem   : 1001 - Opposite Task
 * CPU       : 0.228
 * Memory    : 21592
**/
import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int t,a,b,sum;
		t = s.nextInt();
		
		for(int i = 1;i<=t;i++) {
			sum = s.nextInt();
			
			a = (int) (Math.random()*sum);
			b = sum-a;
			
			if(a>10 || b>10) {
				a = 10;
				b = sum-10;
			}
			
			System.out.printf("%d %d\n",a,b);
		}
	}
}
	
