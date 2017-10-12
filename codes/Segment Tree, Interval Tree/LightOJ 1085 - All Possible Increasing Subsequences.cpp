/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-12 23:07:26
 * Problem   : 1085 - All Possible Increasing Subsequences
 * CPU       : 0.556
 * Memory    : 2740
**/
#include <bits/stdc++.h>

using namespace std;

int BIT[100005];

long long MOD = 1000000007;

void update(int arr[],int n,int i,int val) {
    while(i<=n && i>0) {
        arr[i] = (arr[i]+val) % MOD;
        i+=i&-i;
    }
}

int getSum(int arr[],int n,int i) {
    int sum = 0;
    while(i>0) {
        sum = (sum+arr[i]) % MOD;
        i-=i&-i;
    }
    return sum;
}

void conv(int arr[],int n) {
    int arr2[n];
    copy(arr,arr+n,arr2);
    sort(arr2,arr2+n);
    for(int i = 0;i<n;i++) {
        arr[i] = (int) (lower_bound(arr2,arr2+n,arr[i]) - arr2);
    }
}

int main() {
    //freopen("in","r",stdin);
    int t,n;
    int arr[100005];
    scanf("%d\n",&t);
    for(int i = 0;i<t;i++) {
        scanf("%d",&n);
        memset(BIT,0,sizeof(BIT[0])*(n+1));
        for(int k = 0;k<n;k++) {
            scanf("%d",&arr[k]);
        }
        conv(arr,n);
        long long total = 0;
        for(int k = 0;k<n;k++) {
            long long prev = getSum(BIT,n,arr[k]);
            //cout<<prev<<endl;
            update(BIT,n,arr[k]+1,prev+1);
        }
        long long ans = getSum(BIT,n,n);
        printf("Case %d: %lld\n",i+1,ans%MOD);
    }
    return 0;
}