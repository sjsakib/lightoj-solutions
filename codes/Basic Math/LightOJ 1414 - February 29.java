/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-09-22 10:13:01
 * Problem   : 1414 - February 29
 * CPU       : 0.432
 * Memory    : 25648
**/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);

        int t = in.nextInt();
        Date d1 = new Date();
        Date d2 = new Date();
        int l;
        int dy;
        String s;
        for(int i = 0;i<t;i++) {
            l = 0;
            d1.month = Date.toInt(in.next());
            s = in.next();
            if(s.length() == 3 ) {
                d1.date = Integer.parseInt(""+s.charAt(0)+s.charAt(1));
            } else {
                d1.date = Integer.parseInt(""+s.charAt(0));
            }
            d1.year  = in.nextInt();
            d2.month = Date.toInt(in.next());
            s = in.next();
            if(s.length() == 3 ) {
                d2.date = Integer.parseInt(""+s.charAt(0)+s.charAt(1));
            } else {
                d2.date = Integer.parseInt(""+s.charAt(0));
            }
            d2.year  = in.nextInt();




            if(d1.year == d2.year) {
                if(d1.isLeap() && d1.bleap() && d2.aleap()) {
                    l = 1;
                    System.out.println("Case "+(i+1)+": "+l);
                    continue;
                } else {
                    l = 0;
                    System.out.println("Case "+(i+1)+": "+l);
                }
            } else {
                if(d1.isLeap() && d1.bleap()) {
                    l++;
                    d1.year++;
                } else  {
                    d1.year++;
                }
                if(d2.isLeap() && d2.aleap()) {
                    l++;
                    d2.year--;
                } else {
                    d2.year--;
                }
                if(d1.year == d2.year && Date.isLeap(d1.year) && Date.isLeap(d2.year)) {
                    l++;
                    System.out.println("Case "+(i+1)+": "+l);
                    continue;
                } else if(d1.year == d2.year) {
                    System.out.println("Case "+(i+1)+": "+l);
                    continue;
                }
                if(d1.year%100!=0 && ((d1.year/100)+1)*100<d2.year ) {
                    dy = ((d1.year/100)+1)*100 - d1.year;
                    l+=(dy/4);
                    d1.year = ((d1.year/100)+1)*100;
                    if(d1.year == d2.year && Date.isLeap(d1.year) && Date.isLeap(d2.year)) {
                        l++;
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    } else if(d1.year == d2.year) {
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    }
                } else if(((d1.year/100)+1)*100>d2.year)  {
                    if(d1.year%4 != 0 && ((d1.year/4)+1)*4<d2.year) {
                        d1.year = ((d1.year/4)+1)*4;
                    } else if(((d1.year/4)+1)*4<d2.year) {
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    } else if(((d1.year/4)+1)*4==d2.year) {
                        l++;
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    } else if(((d1.year/4)+1)*4>d2.year) {
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    }
                    if(d1.year == d2.year && Date.isLeap(d1.year) && Date.isLeap(d2.year)) {
                        l++;
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    } else if(d1.year == d2.year) {
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    }
                    dy = d2.year - d1.year;
                    l+=(dy/4+1);
                    System.out.println("Case "+(i+1)+": "+l);
                    continue;
                } else if(((d1.year/100)+1)*100==d2.year) {
                    dy = d2.year - d1.year;
                    l+=(dy/4);
                    if(Date.isLeap(d1.year)) {
                        l++;
                    }
                    if(d2.year % 100 == 0 && d2.year % 400!=0) {
                        l--;
                    }
                    System.out.println("Case "+(i+1)+": "+l);
                    continue;
                }
                dy = d2.year - d1.year;
                l+=(dy/4+1);
                l-=(dy/100+1);
                if(d1.year%400!=0 && ((d1.year/400)+1)*400<d2.year) {
                    d1.year = ((d1.year/400)+1)*400;
                    if(d1.year == d2.year && Date.isLeap(d1.year) && Date.isLeap(d2.year)) {
                        l++;
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    } else if(d1.year == d2.year) {
                        System.out.println("Case "+(i+1)+": "+l);
                        continue;
                    }
                } else if(((d1.year/400)+1)*400==d2.year) {
                    l++;
                    System.out.println("Case "+(i+1)+": "+l);
                    continue;
                }
                dy = d2.year - d1.year;
                l+=(dy/400+1);
                System.out.println("Case "+(i+1)+": "+l);
            }
        }
                
            
    }
}

class Date {
    int month;
    int date;
    int year;

    Date(String m,int date_,int year_) {
        date = date_;
        year = year_;
        month = toInt(m);
    }
    Date() {
        month = 1;
        date = 1;
        year = 2016;
    }

    static int toInt(String s) {
        if(s.compareTo("January") == 0) {
            return 1;
        } else if(s.compareTo("February") == 0) {
            return 2;
        } else if(s.compareTo("March") == 0) {
            return 3;
        } else if(s.compareTo("April") == 0) {
            return 4;
        } else if(s.compareTo("May") == 0) {
            return 5;
        } else if(s.compareTo("June") == 0) {
            return 6;
        } else if(s.compareTo("July") == 0) {
            return 7;
        } else if(s.compareTo("August") == 0) {
            return 8;
        } else if(s.compareTo("September") == 0) {
            return 9;
        } else if(s.compareTo("October") == 0) {
            return 10;
        } else if(s.compareTo("November") == 0) {
            return 11;
        } else if(s.compareTo("December") == 0) {
            return 12;
        }
        return -1;
    }

    boolean bleap() {
        return (month<=2);
    }
    boolean aleap() {
        return (month > 2 ||(month == 2 && date == 29));
    }
    boolean isLeap() {
        if(year%100 == 0) {
            if(year%400 == 0) {
                return true;
            } else {
                return false;
            }
        } else if(year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
    static boolean isLeap(int year) {
        if(year%100 == 0) {
            if(year%400 == 0) {
                return true;
            } else {
                return false;
            }
        } else if(year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
