/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-20 13:37:08
 * Problem   : 1016 - Brush (II)
 * CPU       : 0.164
 * Memory    : 1760
**/
#include <bits/stdc++.h>
#include <cstdio>

#define mem(x,y) memset(x,y,sizeof x);
#define vi vector<int>
#define pi pair<int,int>

using namespace std;
int main() {
    //freopen("in","r",stdin);
    int d[50001];
    int t,n,w,x;
    int r;
    scanf("%d",&t);
    for(int i = 0;i<t;i++) {
        scanf("%d %d",&n,&w);
        for(int j = 0;j<n;j++) {
            scanf("%d %d",&d[j],&d[j]);
        }
        sort(d,d+n);
        x = 0;
        r = 0;
        while(x<n) {
            x = (upper_bound(d,d+n,d[x]+w)-d);
            //cout<<n<<' '<<x<<endl;
            r++;
        }
        printf("Case %d: %d\n",i+1,r);
    }
    return 0;
}