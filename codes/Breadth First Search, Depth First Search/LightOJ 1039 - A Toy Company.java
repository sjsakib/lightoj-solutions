/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-10-11 10:35:53
 * Problem   : 1039 - A Toy Company
 * CPU       : 0.436
 * Memory    : 47380
**/
import java.util.*;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        int t = in.nextInt();
        int n,moves;
        String start,end,a,b,c;
        Graph g = new Graph();
        for(int i = 0;i<t;i++) {
            start = in.nextString();
            end = in.nextString();
            n = in.nextInt();
            for(int k = 0;k<n;k++ ) {
                a = in.nextString();
                b = in.nextString();
                c = in.nextString();
                g.mark(a,b,c);
            }
            moves = g.bfs(start,end);
            g.clear();
            System.out.println("Case "+(i+1)+": "+moves);
            
        }
    }
}
class Graph {
    boolean visited[][][];
    byte level[][][];
    byte[] a = {1,-1,0,0,0,0};
    byte[] b = {0,0,1,-1,0,0};
    byte[] c = {0,0,0,0,1,-1};
    Graph() {
        visited = new boolean[26][26][26];
        level = new  byte[26][26][26];
    }
    void mark(String a,String b,String c) {
        byte[] x = a.getBytes();
        byte[] y = b.getBytes();
        byte[] z = c.getBytes();

        for(int i = 0;i<x.length;i++) {
            for(int j = 0;j<y.length;j++) {
                for(int k = 0;k<z.length;k++) {
                    visited[x[i]-97][y[j]-97][z[k]-97] = true;
                }
            }
        }
    }
    int bfs(String start_,String end_) {
        byte[] start = start_.getBytes();
        byte[] end = end_.getBytes();
        
        for(int i = 0;i<3;i++) {
            start[i] = (byte) (start[i] - 97);
            end[i]  = (byte) (end[i] - 97);
        }
        
        if(visited[start[0]][start[1]][start[2]] == true) {
            return -1;
        }
        
        if(visited[end[0]][end[1]][end[2]] == true) {
            return -1;
        }
        
        if(start[0] == end[0] && start[1] == end[1] && start[2] == end[2]) {
            return level[end[0]][end[1]][end[2]];
        }
        
        Queue<byte[]> q = new LinkedList<byte[]>();
        q.add(start);
        level[start[0]][start[1]][start[2]] = 0;
        byte[] u;
        byte e,f,g;
        while(q.size()!=0) {
            u = q.remove();
            for(int i = 0;i<6;i++) {
                if(u[0] == 0 && a[i] == -1) e = 25;
                else  e = (byte) ((u[0]+a[i])%26);
                
                if(u[1] == 0 && b[i] == -1) f = 25;
                else  f = (byte) ((u[1]+b[i])%26);
                
                if(u[2] == 0 && c[i] == -1) g = 25;
                else  g = (byte) ((u[2]+c[i])%26);
                
                byte[] v = {e,f,g};
                
                if(visited[v[0]][v[1]][v[2]] == false ) {
                    visited[v[0]][v[1]][v[2]] = true;
                    q.add(v);
                    level[v[0]][v[1]][v[2]] =(byte) (level[u[0]][u[1]][u[2]]+1);
                    
                    if(v[0] == end[0] && v[1] == end[1] && v[2] == end[2]) {
                        return level[end[0]][end[1]][end[2]];
                    }
                }
            }
        }
        return -1;
    }
    void clear() {
        for(int i = 0;i<26;i++) {
            for(int j = 0;j<26;j++) {
                for(int k = 0;k<26;k++) {
                    visited[i][j][k] = false;
                    level[i][j][k] = 0;
                }
            }
        }
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
