/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-02 14:50:54
 * Problem   : 1048 - Conquering Keokradong
 * CPU       : 0.056
 * Memory    : 1688
**/
#include <bits/stdc++.h>
#include <cstdio>

#define vi vector<int>

using namespace std;

int main() {
    int t;
    scanf("%d",&t);
    int n,k;
    int j,dayWalk,night;
    int low = 0,mid,high = 0;
    int ght;
    vi arr;
    arr.resize(1001);
    vi res;
    res.resize(301);
    for(int i = 0;i<t;i++) {
        scanf("%d %d",&n,&k);
        ght = n-k;
        for(j = 0;j<=n;j++) {
            scanf("%d",&arr[j]);
            high += arr[j];
            if(arr[j]<low) low = arr[j];
        }
        while(high>=low) {
            mid = (low+high)/2;
            j = 0;
            dayWalk = 0;
            night = 0;
            while(j<=n && night<=k) {
                if(arr[j]>mid) break;
                dayWalk+=arr[j++];
                while(dayWalk+arr[j]<=mid && j-night<=ght) {
                    dayWalk+=arr[j++];
                }
                res[night++] = dayWalk;
                dayWalk = 0;
            }
            if(high==low) break;
            if(j  == n+1) {
                high = mid;
            } else {
                low = mid+1;
            }
        }
        printf("Case %d: %d\n",i+1,mid);
        for(j = 0;j<=k;j++) {
        printf("%d\n",res[j]);
        }
    }
        
    return 0;
}
