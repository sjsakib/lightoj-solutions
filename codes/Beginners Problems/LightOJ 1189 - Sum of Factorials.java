/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-28 22:35:58
 * Problem   : 1189 - Sum of Factorials
 * CPU       : 0.716
 * Memory    : 48592
**/

import java.util.Scanner;
import java.util.Stack;
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
    static long[] facts;
    public static void main(String[] args) {
        facts = new long[21];
        
        fact(20);
        facts[0] = 1;
        facts[1] = 1;
        facts[2] = 2;
        FasterScanner in = new FasterScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        long n;
        long sum;
        long dif;
        boolean found;
        Stack<F> stack1 = new Stack<F>();
        Stack<F> stack2 = new Stack<F>();
        
        
        
        for(int i = 0;i<t;i++) {
            stack1.clear();
            stack2.clear();
            found = false;
            sum = 0;
            n = in.nextLong();
            for(int k = 0;facts[k]<=n;k++) {
                stack1.push(new F(k,facts[k]));
                sum+=facts[k];
            }
            dif = sum - n;
            while(!stack1.empty()) {
                if(dif == 0) {
                    found = true;
                    while(!stack1.empty()) {
                        stack2.push(stack1.pop());
                    }
                } else if(dif>=stack1.peek().f) {
                    sum-=stack1.pop().f;
                } else if(dif<stack1.peek().f) {
                    stack2.push(stack1.pop());
                }
                dif = sum - n;
                if(dif == 0) {
                    found = true;
                }
            }
            if(!found) {
                out.println("Case "+(i+1)+": impossible");
            } else {
                out.print("Case "+(i+1)+": ");
                while(!stack2.empty()) {
                    out.print(stack2.pop().num);
                    if(!stack2.empty()) {
                        out.print("!+");
                    } else {
                        out.print("!");
                    }
                } 
                out.print("\n");
            }
        }
        out.flush();
        out.close();
        
    }
    
    static long fact(long n) {
        if(n<0) {
            return 1;
        }
        else if(n==2) {
            return 2;
        }
        else if(facts[(int)n]!=0) {
            return facts[(int)n];
        } else {
            facts[(int)n] = n*fact(n-1);
            return n*fact(n-1);
        }
    }
    
}
class F {
        long num;
        long f;
        F(long  n,long f_) {
            num = n;
            f = f_;
        }
    }
