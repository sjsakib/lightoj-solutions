/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-06-14 16:36:59
 * Problem   : 1109 - False Ordering
 * CPU       : 0.588
 * Memory    : 26876
**/
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Num[] nums = new Num[1001];
        for(int i = 0;i<nums.length;i++) {
            nums[i] = new Num(i);
            for(int k = 1;k<=Math.sqrt(i);k++) {
                if(i%k == 0) {
                    nums[i].divs++;
                }
            }
            if(isSq(i)) nums[i].divs = nums[i].divs*2-1;
            else nums[i].divs*= 2;
        }
        mergeSort(nums,1,nums.length-1);
        int t;
        t = in.nextInt();
        int n;
        for(int i = 0;i<t;i++) {
            n = in.nextInt();
            System.out.printf("Case %d: %d\n",i+1,nums[n].num);
        }
        
    }



    
    static boolean isSq(int n) {
        double x = Math.sqrt(n);
        int y = (int) x;
        if( x == y) return true;
        else return false;
    }
    
    static void merge(Num[] nums,int p,int q,int r) {
        int n1 = q-p+1;
        int n2 = r-q;
        Num[] A = new Num[n1+1];
        Num[] B = new Num[n2+1];
        for(int i = 0;i<A.length-1;i++) {
            A[i] = nums[p+i];
        }
        for(int i = 0;i<B.length-1;i++) {
            B[i] = nums[q+i+1];
        }
        A[n1] = new Num(0);
        B[n2] = new Num(0);
        A[n1].divs = 10000;
        B[n2].divs = 10000;
        int i = 0;
        int j = 0;
        for(int k = p;k<=r;k++) {
            if(A[i].divs<B[j].divs) {
                nums[k] = A[i];
                i++;
            } else if(A[i].divs>B[j].divs) {
                nums[k] = B[j];
                j++;
            } else {
                if(A[i].num>B[j].num) {
                    nums[k] = A[i];
                    i++;
                } else {
                    nums[k] = B[j];
                    j++;
                }
            }
        }
    }
    
    static void mergeSort(Num[] nums,int p,int r) {
        if(r>p) {
            int q = (r+p)/2;
            mergeSort(nums,p,q);
            mergeSort(nums,q+1,r);
            merge(nums,p,q,r);
        }
    }

}

class Num {
    int num;
    int divs;
    Num(int n) {
        num = n;
        divs = 0;
    }
}
