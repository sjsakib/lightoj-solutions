/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-04 08:40:13
 * Problem   : 1113 - Discover the Web
 * CPU       : 0.680
 * Memory    : 29960
**/
import java.util.Scanner;
import java.util.Stack;
class Browser {
    String url;
    Stack<String> forward;
    Stack<String> backward;
    
    Browser(String home) {
        url = home;
        forward = new Stack<String>();
        backward = new Stack<String>();
    }
    
    void visit(String url_) {
        backward.push(url);
        url = url_;
        forward.clear();
    }
    boolean back() {
        if(!backward.empty()){
            forward.push(url);
            url =backward.pop();
            return false;
        } else {
            return true;
        }
    }
    boolean forward() {
        if(!forward.empty()) {
            backward.push(url);
            url =forward.pop();
            return false;
        } else {
            return true;
        }
    }
    void reset() {
        forward.clear();
        backward.clear();
        url = "http://www.lightoj.com/";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Browser b = new Browser("http://www.lightoj.com/");
        
        int t;
        String cmd;
        String url;
        boolean ignored;
        t = in.nextInt();
        for(int i = 0;i<t;i++) {
            System.out.print("Case ");
            System.out.print(i+1);
            System.out.println(":");
            cmd = in.next();
            while(!cmd.equals("QUIT")) {
                ignored = false;
                if(cmd.equals("VISIT")) {
                    url = in.next();
                    b.visit(url);
                } else if(cmd.equals("BACK")) {
                    ignored = b.back();
                } else if(cmd.equals("FORWARD")) {
                    ignored = b.forward();
                }
                if(ignored) {
                    System.out.println("Ignored");
                } else {
                    System.out.println(b.url);
                }
                cmd = in.next();
            }
            b.reset();
        }
    }
}
