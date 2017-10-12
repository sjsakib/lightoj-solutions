/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-22 09:02:06
 * Problem   : 1425 - The Monkey and the Oiled Bamboo
 * CPU       : 0.260
 * Memory    : 1960
**/
#include <bits/stdc++.h>

using namespace std;

int main() {
    //freopen("in","r",stdin);
    int t;
    scanf("%d",&t);
    int n,arr[100009];
    for(int i = 1;i<=t;i++) {
        scanf("%d",&n);
        arr[0] = 0;
        for(int j = 1;j<=n;j++) {
            scanf("%d",arr+j);
        }
        int mx=0,cur;
        for(int j = 1;j<=n;j++) mx = max(mx,arr[j]-arr[j-1]);
        cur = mx;
        for(int j = 1;j<=n;j++) {
            int dis = arr[j]-arr[j-1];
            if( dis > cur) {
                mx++;
                break;
            } else if(dis == cur) cur--;
        }
        printf("Case %d: %d\n",i,mx );
    }    
    return 0;
}