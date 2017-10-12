/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-08-07 23:10:57
 * Problem   : 1212 - Double Ended Queue
 * CPU       : 0.424
 * Memory    : 26120
**/
import java.util.Scanner;

class Node {
    int num;
    Node next;
    Node prev;
    Node(int n) {
        num = n;
        next = null;
        prev = null;
    }
}


class Deque {
    int maxSize;
    int currentSize;
    Node left;
    Node right;
    
    Deque(int size_) {
        left = right = null;
        maxSize = size_;
        currentSize = 0;
    }
    void pushLeft(Node n) {
        if(currentSize<maxSize) {
            if(currentSize == 0) {
                left = right = n;
            } else {
                n.next = left;
                left.prev = n;
                left = n;
            }
            System.out.println("Pushed in left: "+n.num);
            currentSize++;
        } else {
            System.out.println("The queue is full");
        }
    }
    
    void pushRight(Node n) {
        if(currentSize<maxSize) {
            if(currentSize == 0) {
                left = right = n;
            } else {
                n.prev = right;
                right.next = n;
                right = n;
            }
            currentSize++;
            System.out.println("Pushed in right: "+n.num);
        } else {
            System.out.println("The queue is full");
        }
    }
    void popLeft() {
        if(currentSize>0) {
            System.out.println("Popped from left: "+left.num);
            try {
                left = left.next;
                left.prev = null;
            } catch (Exception e) {
                left = null;
            }
            currentSize--;
        } else {
            System.out.println("The queue is empty");
        }
    }
    void popRight() {
        if(currentSize>0) {
            System.out.println("Popped from right: "+right.num);
            try {
                right = right.prev;
                right.next = null;
            } catch (Exception e) {
                right = null;
            }
            currentSize--;
        } else {
            System.out.println("The queue is empty");
        }
    }
    void clr(int n) {
        left = right = null;
        maxSize = n;
        currentSize = 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        int size,coms,x;
        String com;
        Deque deque = new Deque(0);
        for(int i = 0;i<t;i++) {
            
            size = in.nextInt();
            coms = in.nextInt();
            deque.clr(size);
            System.out.println("Case "+(i+1)+":");
            for(int j = 0;j<coms;j++) {
                com = in.next();
                if(com.equals("pushLeft")) {
                    x = in.nextInt();
                    deque.pushLeft(new Node(x));
                } else if(com.equals("pushRight")) {
                    x = in.nextInt();
                    deque.pushRight(new Node(x));
                } else if(com.equals("popLeft")) {
                    deque.popLeft();
                } else {
                    deque.popRight();
                }
            }
        }
    }
}
