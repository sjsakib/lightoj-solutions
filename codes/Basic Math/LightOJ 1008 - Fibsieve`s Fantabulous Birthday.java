/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-05-26 07:23:39
 * Problem   : 1008 - Fibsieve`s Fantabulous Birthday
 * CPU       : 0.324
 * Memory    : 22592
**/
import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		long s;
		int n,x,y,dx;
		
		for(int i = 0;i<t;i++) {
			s = in.nextLong();
			n =(int) Math.sqrt(s);
			dx =(int) s - (n*n);
			
			if(n%2!=0) {
				y = n;
				x = 1;
				if(dx>0) {
					y++;
					dx--;
				}
				if(dx>n) {
					x+=n;
					dx-=n;
				} else {
					x+=dx;
					dx = 0;
				}
				y-=dx;
			} else {
				x = n;
				y = 1;
				if(dx>0) {
					x++;
					dx--;
				}
				if(dx>n) {
					y+=n;
					dx-=n;
				} else {
					y+=dx;
					dx = 0;
				}
				x-=dx;
			}
			System.out.printf("Case %d: %d %d\n",i+1,x,y);
		}
	}
}
