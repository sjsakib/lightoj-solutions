/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-10 09:51:59
 * Problem   : 1249 - Chocolate Thief
 * CPU       : 0.696
 * Memory    : 48164
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int t = in.nextInt();
        int n;
        int gen = 0;
        Student[] st = new Student[100];
        Student thief;
        Student victim;
        for(int i = 0;i<t;i++) {
            thief = null;
            victim = null;
            n = in.nextInt();
            for(int k = 0;k<n;k++) {
                st[k] = new Student(in.next(),in.nextInt()*in.nextInt()*in.nextInt());
            }
            if(n == 2) {
                if(st[0].choco>st[1].choco) {
                    System.out.println("Case "+(i+1)+": "+st[0].name+" took chocolate from "+st[1].name);
                } else if(st[0].choco<st[1].choco) {
                    System.out.println("Case "+(i+1)+": "+st[1].name+" took chocolate from "+st[0].name);
                } else {
                    System.out.println("Case "+(i+1)+": no thief");
                }
                continue;
            } else if(n==3) {
                if(st[0].choco>st[1].choco && st[0].choco>st[2].choco) {
                    thief = st[0];
                } else if(st[1].choco>st[0].choco && st[1].choco>st[2].choco) {
                    thief = st[1];
                } else if(st[2].choco>st[0].choco&&st[2].choco>st[1].choco) {
                    thief = st[2];
                }
                if(st[0].choco<st[1].choco&&st[0].choco<st[2].choco) {
                    victim = st[0];
                } else if(st[1].choco<st[0].choco&&st[1].choco<st[2].choco) {
                    victim = st[1];
                } else if(st[2].choco<st[0].choco&&st[2].choco<st[1].choco) {
                    victim = st[2];
                }
            } else {
                for(int k = 0;k<n-1;k++) {
                    for(int x = k+1;x<n;x++) {
                        if(st[k].choco == st[x].choco) {
                            gen = st[k].choco;
                            break;
                        }
                    }
                }
                for(int k = 0;k<n;k++) {
                    if(st[k].choco>gen) {
                        thief = st[k];
                    } else if(st[k].choco<gen) {
                        victim = st[k];
                    }
                }
            }
            if(thief == null) {
                System.out.println("Case "+(i+1)+": no thief");
            } else {
                System.out.println("Case "+(i+1)+": "+thief.name+" took chocolate from "+victim.name);
            }
        }
    }
}
class Student {
    String name;
    int choco;
    Student(String name_,int choco_) {
        name = name_;
        choco = choco_;
    }
}
