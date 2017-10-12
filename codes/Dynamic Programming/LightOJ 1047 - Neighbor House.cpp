/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-17 20:59:09
 * Problem   : 1047 - Neighbor House
 * CPU       : 0.000
 * Memory    : 1688
**/
#include <bits/stdc++.h>
#include <cstdio>

#define mem(x,y) memset(x,y,sizeof x)
#define vi vector<int>
#define pi pair<int,int>
#define min3(x,y,z) min(min(x,y),z)
#define inf 100000

using namespace std;

int dp[21][3];
int cost[21][3];
int n;

int call(int i,int c) {
    if(c>0) {
        if(dp[i][c]!=-1) return dp[i][c];
    }
    if(i==n) return 0;


    int ret1 = inf,ret2 = inf,ret3 = inf;
    if(c!=0) {
        ret1 = cost[i][0]+call(i+1,0);
    }
    if(c!=1) {
        ret2 = cost[i][1]+call(i+1,1);
    }
    if(c!=2) {
        ret3 = cost[i][2]+call(i+1,2);
    }
    return dp[i][abs(c)] = min3(ret1,ret2,ret3);

}


int main() {
    //freopen("in","r",stdin);
    int t;
    scanf("%d",&t);
    for(int i = 0;i<t;i++) {
        mem(dp,-1);
        scanf("%d",&n);
        for(int j = 0;j<n;j++) {
            scanf("%d %d %d",&cost[j][0],&cost[j][1],&cost[j][2]);
        }
        printf("Case %d: %d\n",i+1,call(0,-1));
    }
    return 0;
}
