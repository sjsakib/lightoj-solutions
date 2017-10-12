/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-16 15:41:21
 * Problem   : 1202 - Bishops
 * CPU       : 0.356
 * Memory    : 23416
**/
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.io.PrintWriter;

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

public class Main {
    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        PrintWriter out = new PrintWriter(System.out);

        int x1,x2,y1,y2;
        int dx,dy;
        int t;
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
            x1 = in.nextInt();
            y1 = in.nextInt();
            x2 = in.nextInt();
            y2 = in.nextInt();
            dx = Math.abs(x1 - x2);
            dy = Math.abs(y1 - y2);
            out.print("Case ");
            out.print(i+1);
            out.print(": ");

            if(!((x1%2 == 0 ^ y1%2 == 0) ^ (x2%2 == 0 ^ y2%2 == 0))) {
                if(dx == dy ) {
                    out.println(1);
                } else {
                    out.println(2);
                }
            } else {
                out.println("impossible");
            }
        }
        out.flush();
        out.close();
    }
}
