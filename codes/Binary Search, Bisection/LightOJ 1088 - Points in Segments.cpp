/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-01 14:25:41
 * Problem   : 1088 - Points in Segments
 * CPU       : 0.296
 * Memory    : 2080
**/
#include <bits/stdc++.h>
#include <cstdio>

#define vi vector<int>
using namespace std;

int main() {
    int t;
    int n,q;
    int a,b;
    vi::iterator low,up;
    scanf("%d",&t);
    for(int i = 0;i<t;i++) {
        scanf("%d %d",&n,&q);
        vector<int> arr;
        arr.resize(n);
        for(int k = 0;k<n;k++) {
            scanf("%d",&arr[k]);
        }
        printf("Case %d:\n",i+1);
        for(int k = 0;k<q;k++) {
            scanf("%d %d",&a,&b);
            low = lower_bound(arr.begin(),arr.end(),a);
            up = upper_bound(arr.begin(),arr.end(),b);
            printf("%d\n",up-low);
        }
    }
    return 0;
}
