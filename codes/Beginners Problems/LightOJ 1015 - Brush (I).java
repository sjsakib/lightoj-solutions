/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-05-30 05:07:18
 * Problem   : 1015 - Brush (I)
 * CPU       : 0.456
 * Memory    : 29864
**/
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		int s,t2,x;
		String[] input;
		for(int i = 0;i<t;i++) {
			s = 0;
            br.readLine();
			t2 = Integer.parseInt(br.readLine());
			input = br.readLine().split(" ");
			for(int j = 0;j<t2;j++) {
				x = Integer.parseInt(input[j]);
				if(x>0) s+=x;
			}
			System.out.printf("Case %d: %d\n",i+1,s);
		}
	}
}
