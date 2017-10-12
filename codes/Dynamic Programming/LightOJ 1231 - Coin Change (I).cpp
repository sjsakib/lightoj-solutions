/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-17 00:38:47
 * Problem   : 1231 - Coin Change (I)
 * CPU       : 0.276
 * Memory    : 1884
**/
#include <bits/stdc++.h>
#include <cstdio>

#define mem(x,y) memset(x,y,sizeof x);
#define vi vector<int>
#define pi pair<int,int>

using namespace std;

int t,n,make;

int coin[51];
int cnt[51];
int dp[50][1001];

int call(int i,int amount) {
    if(i>=n) {
        if(amount == 0) return 1;
        else return 0;
    }
    if(dp[i][amount]!=-1) return dp[i][amount];
    int ret1 = 0,ret2 = 0;
    int nAmount = amount;
    for(int j = 0;j<cnt[i];j++) {
        if(nAmount-coin[i]>=0) {
            ret1+= (call(i+1,nAmount-coin[i]))%100000007;
            nAmount-=coin[i];
        } else break;
    }
    ret2 = call(i+1,amount);
    return dp[i][amount] = (ret1+ret2)%100000007;
}


int main() {
    //freopen("in","r",stdin);
    scanf("%d",&t);
    for(int i = 0;i<t;i++)  {
        mem(dp,-1);
        scanf("%d %d",&n,&make);
        for(int j = 0;j<n;j++) {
            scanf("%d",&coin[j]);
        }
        for(int j = 0;j<n;j++) {
            scanf("%d",&cnt[j]);
        }
        printf("Case %d: %d\n",i+1,call(0,make));
    }
    return 0;
}