/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-01 09:06:45
 * Problem   : 1010 - Knights in Chessboard
 * CPU       : 0.416
 * Memory    : 31560
**/
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) {
		FasterScanner in = new FasterScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t  = in.nextInt();
		int m,n,x;
		for(int i = 0;i<t;i++) {
			m = in.nextInt();
			n = in.nextInt();
			if(m == 1 || n == 1) x = m*n;
			else if(m == 2 || n == 2) {
				x = (int) Math.ceil((m*n)/2.0);
				if(m*n%8==2) {
					x+=1;
				} else if(m*n%8 == 4) x+=2;
				else if(m*n%8 == 6) x+=1;
			}
			else x = (int) Math.ceil((m*n)/2.0);
			out.print("Case ");
			out.print(i+1);
			out.print(": ");
			out.println(x);
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
