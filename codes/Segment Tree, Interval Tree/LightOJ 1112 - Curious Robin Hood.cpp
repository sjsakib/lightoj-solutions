/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-13 00:48:53
 * Problem   : 1112 - Curious Robin Hood
 * CPU       : 0.260
 * Memory    : 2468
**/
#include <bits/stdc++.h>

using namespace std;

long long BIT[100005];


void update(long long arr[],int n,int i,long long val) {
    while(i<=n && i>0) {
        arr[i] = (arr[i]+val);
        i+=i&-i;
    }
}

long long getSum(long long arr[],int n,int i) {
    long long sum = 0;
    while(i>0) {
        sum = (sum+arr[i]);
        i-=i&-i;
    }
    return sum;
}

int main() {
    //ios::sync_with_stdio(false);
    //freopen("in","r",stdin);
    int t,n,x,q,a,b;
    long long cur;
    scanf("%d",&t);
    for(int i = 0;i<t;i++) {
        scanf("%d %d",&n,&q);
        memset(BIT,0,sizeof(BIT[0])*(n+1));
        for(int k = 0;k<n;k++) {
            scanf("%d",&x);
            update(BIT,n,k+1,x);
        }
        printf("Case %d:\n",i+1 );
        for(int k = 0;k<q;k++) {
            scanf("%d",&x);
            if(x == 1) {
                scanf("%d",&a);
                cur = getSum(BIT,n,a+1);
                if(a>0) cur -= getSum(BIT,n,a);
                update(BIT,n,a+1,-cur);
                printf("%lld\n",cur);
            } else if(x == 2) {
                scanf("%d %d",&a,&b);
                update(BIT,n,a+1,b);
            } else {
                scanf("%d %d",&a,&b);
                cur = getSum(BIT,n,b+1);
                if(a>0) cur-=getSum(BIT,n,a);
                printf("%lld\n",cur);
            }
        }
    }
    return 0;
}