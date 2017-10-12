/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-03 23:48:11
 * Problem   : 1006 - Hex-a-bonacci
 * CPU       : 0.016
 * Memory    : 1956
**/
#include <bits/stdc++.h>
    long int a, b, c, d, e, f;
    long int dp[10001];
    long int fn(long  int n ) {
        if(dp[n]) return dp[n];
        if( n == 0 ) return a;
        if( n == 1 ) return b;
        if( n == 2 ) return c;
        if( n == 3 ) return d;
        if( n == 4 ) return e;
        if( n == 5 ) return f;
        dp[n] = (fn(n-1) + fn(n-2) + fn(n-3) + fn(n-4) + fn(n-5) + fn(n-6))%10000007;
        return dp[n];
    }
    int main() {
        long int n, caseno = 0, cases;
        scanf("%ld", &cases);
        while( cases-- ) {
            memset(dp,0,sizeof dp);
            scanf("%ld %ld %ld %ld %ld %ld %ld", &a, &b, &c, &d, &e, &f, &n);
            printf("Case %ld: %ld\n", ++caseno, fn(n)%10000007);
        }
        return 0;
    }
