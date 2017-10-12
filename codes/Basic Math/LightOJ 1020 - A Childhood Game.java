/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-10-06 11:32:06
 * Problem   : 1020 - A Childhood Game
 * CPU       : 0.804
 * Memory    : 30984
**/
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

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
        FasterScanner in = new FasterScanner(System.in);
        int t = in.nextInt();
        String starter;
        int n;
        boolean alice,bob;
        boolean aliceWin;

        for(int i = 0;i<t;i++) {
            aliceWin = false;
            n = in.nextInt();
            starter  = in.nextString();
            if(starter.compareTo("Alice")==0) {
                alice = true;
                bob = false;
            } else {
                bob = true;
                alice = false;
            }
            if((alice && (n-1)%3!=0)||(bob && n%3 == 0)) {
                aliceWin = true;
            }
            if(aliceWin) {
                System.out.println("Case "+(i+1)+": Alice");
            } else {
                System.out.println("Case "+(i+1)+": Bob");
            }
        }
    }
}
