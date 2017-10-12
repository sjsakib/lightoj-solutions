/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-10-05 11:56:20
 * Problem   : 1303 - Ferris Wheel
 * CPU       : 0.428
 * Memory    : 28488
**/
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        FW fw = new FW();
        LinkedList<P> line = new LinkedList<P>();

        int m,n,time;
        P p;
        int step;

        for(int i = 0;i<t;i++) {
            n = in.nextInt();
            m = in.nextInt();
            time = 0;
            for(int k = 0;k<n;k++) {
                line.add(new P());
            }
            fw.n = m;
            fw.in(line.remove());
            time+=5;
            fw.spin();
            while(!fw.isEmpty() ||line.size()>0) {
                time+=5;
                if(fw.bottom()!= null) {
                    p = fw.out();
                    p.hasRiden(fw.bottomIn());
                    if(!p.done(m)) {
                        line.add(p);
                    }
                }
                if(line.size()!=0) {
                    for(int k = 0;k<line.size();k++) {
                        p = line.get(k);
                        if(!p.didRide(fw.bottomIn())) {
                            fw.in(p);
                            line.remove(k);
                            break;
                        }
                    }
                }
                fw.spin();
            }
            System.out.println("Case "+(i+1)+": "+time);
            line.clear();
        }
    }
}


class P {
    boolean[] riden;
    P() {
        riden = new boolean[25];
    }
    void hasRiden(int n) {
        riden[n-1] = true;
    }
    boolean didRide(int n) {
        return riden[n-1];
    }
    boolean done(int n) {
        for(int i = 0;i<n;i++) {
            if(riden[i] == false) {
                return  false;
            }
        }
        return true;
    }
}
class FW {
    P[] nodes;
    int steps;
    int n;
    FW() {
        nodes = new P[25];
        steps = 0;
    }
    P bottom() {
        return nodes[steps%n];
    }
    P out() {
        P p = nodes[steps%n];
        nodes[steps%n] = null;
        return p;
    }
    void in(P p) {
        nodes[steps%n] = p;
    }
    void spin() {
        steps++;
    }
    boolean isEmpty() {
        boolean empty = true;
        for(int i = 0;i<n;i++) {
            if(nodes[i]!=null) {
                empty = false;
            }
        }
        return empty;
    }
    int bottomIn() {
        return steps%n+1;
    }
}
