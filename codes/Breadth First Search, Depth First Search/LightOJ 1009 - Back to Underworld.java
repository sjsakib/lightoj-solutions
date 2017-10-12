/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-10-07 20:15:55
 * Problem   : 1009 - Back to Underworld
 * CPU       : 0.712
 * Memory    : 55564
**/
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        int t = in.nextInt();
        int n,max;
        int first;

        Graph g = new Graph(20001);

        
        for(int i = 0;i<t;i++) {
            n = in.nextInt();
            first = in.nextInt();
            g.addEdge(first,in.nextInt());
            for(int k = 1;k<n;k++) {
                g.addEdge(in.nextInt(),in.nextInt());
            }
            max = g.bfs(first);
            System.out.println("Case "+(i+1)+": "+max);
        }
    }
}
        
class Graph {
    int nodes;
    ArrayList<Node>[] edges;
    Queue<Node> q;
    boolean[] visited;
    Graph(int n) {
        nodes = n;
        edges = (ArrayList<Node>[]) new ArrayList[n];
        q = new LinkedList<Node>();
        visited = new boolean[nodes];
    }
    void addEdge(int a,int b) {
        if(edges[a] == null) {
            edges[a] = new ArrayList<Node>();
        }
        if(edges[b] == null) {
            edges[b] = new ArrayList<Node>();
        }
        edges[a].add(new Node(b));
        edges[b].add(new Node(a));
    }
    int bfs(int start) {
        Node u,v;
        int max = 0;
        u = new Node(start);
        u.level = 0;
        visited[u.n] = true;
        q.add(u);
        int limit;
        int one = 0,another = 0;
        while(q.size()!=0) {
            u = q.remove();
            if(u.level%2 == 0) {
                one++;
            } else {
                another++;
            }
            limit = edges[u.n].size();
            for(int i = 0;i<limit;i++) {
                v = edges[u.n].get(i);
                if(!visited[v.n]) {
                    v.level = u.level+1;
                    visited[v.n] = true;
                    q.add(v);
                }
            }
            edges[u.n].clear();
        }
        Arrays.fill(visited,false);
        max = Math.max(one,another);
        for(int i = 0;i<20001;i++) {
            if(edges[i] !=null) {
                if(edges[i].size()>0) {
                    max+=bfs(edges[i].get(0).n);
                    break;
                }
            }
        }
        return max;
    }
}
class Node {
    int n;
    int level;
    boolean visited;
    Node(int n_) {
        n = n_;
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
