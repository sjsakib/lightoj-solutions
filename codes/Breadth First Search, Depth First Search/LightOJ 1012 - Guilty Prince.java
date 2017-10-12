/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-10-07 21:38:53
 * Problem   : 1012 - Guilty Prince
 * CPU       : 0.332
 * Memory    : 30860
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
        FasterScanner in = new FasterScanner();
        int t = in.nextInt();
        int w,h,px=0,py=0,cells;
        String s;
        char[][] grid = new char[21][21];
        char c;
        for(int i = 0;i<t;i++) {
            w = in.nextInt();
            h = in.nextInt();
            for(int m = 0;m<h;m++) {
                s = in.nextString();
                for(int n = 0;n<w;n++) {
                    c = s.charAt(n);
                    grid[n][m] = c;
                    if(c == '@') {
                        px = n;
                        py = m;
                    }
                }
            }
            cells = from(px,py,grid,w,h);
            System.out.println("Case "+(i+1)+": "+cells);
        }
    }
    static int from(int x,int y,char[][] grid,int w,int h) {
        boolean valid = (x<w && y<h && x>=0 && y>=0 && grid[x][y]!= '#' && grid[x][y]!= 'x');
        if(valid) {
            grid[x][y] = 'x';
            return 1+from(x+1,y,grid,w,h)+from(x,y+1,grid,w,h)+from(x-1,y,grid,w,h)+from(x,y-1,grid,w,h);
        } else {
            return 0;
        }
    }
}
