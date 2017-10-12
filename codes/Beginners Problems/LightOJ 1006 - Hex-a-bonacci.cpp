/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-09-04 00:51:01
 * Problem   : 1006 - Hex-a-bonacci
 * CPU       : 0.016
 * Memory    : 1916
**/
#include <bits/stdc++.h>

using namespace std;

int mem[10005];
int a, b, c, d, e, f;
int fn( int n ) {
    if( n == 0 )
    {
        mem[n] = a % 10000007;
        return mem[n];
    }
    if( n == 1 )
    {
        mem[n] = b % 10000007;
        return mem[n];
    }
    if( n == 2 )
    {
       mem[n] = c % 10000007;
       return mem[n];
    }
    if( n == 3 )
    {
        mem[n] = d % 10000007;
        return mem[n];
    }
    if( n == 4 )
    {
        mem[n] = e % 10000007;
        return mem[n];
    }
    if( n == 5 )
    {
        mem[n] = f % 10000007;
        return mem[n];
    }
    if(mem[n] != -1)
    {
        return mem[n];
    }
    if(mem[n] == -1)
    {
        mem[n] = (fn(n-1) + fn(n-2) + fn(n-3) + fn(n-4) + fn(n-5) + fn(n-6) )% 10000007 ;
        return mem[n];
    }
}
int main() {
    int n, caseno = 0, cases;
    memset(mem, -1, sizeof(mem));
    scanf("%d", &cases);
    while( cases-- ) {
        scanf("%d %d %d %d %d %d %d", &a, &b, &c, &d, &e, &f, &n);
        printf("Case %d: %d\n", ++caseno, fn(n) % 10000007);
        memset(mem, -1, sizeof(mem));
    }
    return 0;
}