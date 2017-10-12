/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-02-12 13:11:01
 * Problem   : 1319 - Monkey Tradition
 * CPU       : 0.032
 * Memory    : 1688
**/
#include <bits/stdc++.h>

using namespace std;

long long  mod_inverse(long long a, long long m) {
    long long m0 = m, t, q;
    long long x0 = 0, x1 = 1;

    if (m == 1) return 0;
 
    while (a > 1) {
        // q is quotient
        q = a / m;
        t = m;
        // m is remainder now, process same as
        // Euclid's algo
        m = a % m, a = t;
        t = x0;
        x0 = x1 - q * x0;
        x1 = t;
    }

    // Make x1 positive
    if (x1 < 0) x1 += m0;
    return x1;
}

int main() {
    //freopen("in","r",stdin);
    int t;
    scanf("%d",&t);
    vector<int> mi,ai;
    int n,m,a;
    long long mul,sum;

    for(int i = 0;i<t;i++) {
        scanf("%d",&n);
        mul = 1;
        sum = 0;
        for(int j = 0;j<n;j++) {
            scanf("%d %d",&m,&a);
            mi.push_back(m);
            ai.push_back(a);
            mul*=m;
        }
        for(int j = 0;j<n;j++) {
          sum = (sum + (mul/mi[j]*mod_inverse(mul/mi[j],mi[j])*ai[j]) % mul) % mul;
        }
        printf("Case %d: %lld\n",i+1,sum );
        mi.clear();
        ai.clear();
    }
}