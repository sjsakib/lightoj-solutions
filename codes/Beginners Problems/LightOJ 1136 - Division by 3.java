/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-15 08:08:16
 * Problem   : 1136 - Division by 3
 * CPU       : 0.324
 * Memory    : 23512
**/
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
public class Main {
    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        long n1,n2;
        long n;
        int t;
        int r;
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
            r = 0;
            n1 = in.nextLong();
            n2 = in.nextLong();
            n = n2-n1+1;
            if(((n1*n1)+n1)/2 %3 == 0) {
                n--;
                r++;
                n1++;
                if(n>0 && ((n1*n1)+n1)/2%3 ==0 ) {
                    n--;
                    r++;
                }
            }
            if(n>0 && ((n2*n2)+n2)/2 %3 != 0) {
                n--;
                
            } else if(n>1 && ((n2*n2)+n2)/2 %3 == 0 && (((n2-1)*(n2-1))+n2-1)/2 %3 != 0) {
                n-=2;
                r+=1;
            } else if(n==1 && ((n2*n2)+n2)/2 %3 == 0) {
                n--;
                r++;
            }
            
            r+=((n*2)/3);
            out.print("Case ");
            out.print(i+1);
            out.print(": ");
            out.println(r);
        }
        out.flush();
        out.close();
    }
}

class FasterScanner {
	private InputStream mIs;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public FasterScanner() {
		this(System.in);
	}

	public FasterScanner(InputStream is) {
		mIs = is;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = mIs.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public String nextLine() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isEndOfLine(c));
		return res.toString();
	}

	public String nextString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public long nextLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public int nextInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public boolean isEndOfLine(int c) {
		return c == '\n' || c == '\r' || c == -1;
	}

}


