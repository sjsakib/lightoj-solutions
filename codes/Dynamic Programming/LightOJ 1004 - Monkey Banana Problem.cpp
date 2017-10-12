/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-17 12:56:30
 * Problem   : 1004 - Monkey Banana Problem
 * CPU       : 0.076
 * Memory    : 1768
**/
#include <bits/stdc++.h>
#include <cstdio>

#define mem(x,y) memset(x,y,sizeof x);
#define vi vector<int>
#define pi pair<int,int>

using namespace std;
int n;
int dp[10001];
int banana[10001];

int call(int x,int h) {
    int mn,mx,h2;
    h2 = 2*n-h;
    if(h<n) {
        mn = (h*(h-1))/2+1;
        mx = (h*(h+1))/2;
        if(x>mx || x<mn) {
            return 0;
        }
    } else {
        mn = n*n - (h2*(h2+1))/2+1;
        mx = n*n - (h2*(h2-1))/2;;
        if(x>mx || x<mn) return 0;
    }
    if(dp[x]!=-1) return dp[x];
    int ret1, ret2;
    if(h<n) {
        ret1 = banana[x]+call(x+h,h+1);
        ret2 = banana[x]+call(x+h+1,h+1);
    } else {
        ret1 = banana[x]+call(x+h2,h+1);
        ret2 = banana[x]+call(x+h2-1,h+1);
    }
    return dp[x] = max(ret1,ret2);
}

int main() {
    int t;
    int x;
    //freopen("in","r",stdin);
    scanf("%d",&t);
    for(int i = 0;i<t;i++) {
        scanf("%d",&n);
        memset(dp,-1,sizeof(dp[0])*(n*n+1));
        for(int j = 1;j<=n*n;j++) {
            scanf("%d",&banana[j]);
        }
        printf("Case %d: %d\n",i+1,call(1,1));
    }
    return 0;
}
