/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-14 14:11:24
 * Problem   : 1266 - Points in Rectangle
 * CPU       : 0.364
 * Memory    : 6620
**/
#include <bits/stdc++.h>

using namespace std;

int arr[1005][1005];
bool arr2[1005][1005];

void updateH(int n,int i,int j,int val) {
    while(j<=n) {
        arr[i][j]+=val;
        j+=j&-j;
    }
}

void update(int n,int i,int j,int val) {
    while(i<=n) {
        updateH(n,i,j,val);
        i+=i&-i;
    }
}

int getSumH(int n,int i,int j) {
    int sum = 0;
    while(j>0) {
        sum = (sum+arr[i][j]);
        j-=j&-j;
    }
    return sum;
}

int getSum(int n,int i,int j) {
    int sum = 0;
    while(i>0) {
        sum += getSumH(n,i,j);
        i-=i&-i;
    }
    return sum;
}

int main() {
    //freopen("in","r",stdin);
    int t,q,x1,x2,y1,y2,c,ans;
    scanf("%d",&t);
    for(int i = 0;i<t;i++) {
        scanf("%d",&q);
        memset(arr,0,sizeof(arr));
        memset(arr2,0,sizeof(arr2));
        printf("Case %d:\n",i+1);
        while(q--) {
            scanf("%d",&c);
            if(c == 0) {
                scanf("%d %d",&x1,&y1);
                x1++,y1++;
                if(!arr2[x1][y1]) {
                    update(1001,x1,y1,1);
                    arr2[x1][y1] = true;
                }
            } else {
                scanf("%d %d %d %d",&x1,&y1,&x2,&y2);
                x1++,y1++,x2++,y2++;
                int ans = getSum(1001,x2,y2);
                ans-=getSum(1001,x1-1,y2);
                ans-=getSum(1001,x2,y1-1);
                ans+=getSum(1001,x1-1,y1-1);
                printf("%d\n",ans);
            }
        }
    }
    return 0;
}