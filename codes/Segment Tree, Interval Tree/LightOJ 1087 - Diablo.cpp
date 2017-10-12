/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-16 17:26:48
 * Problem   : 1087 - Diablo
 * CPU       : 0.304
 * Memory    : 3252
**/
#include <bits/stdc++.h>

using namespace std;

int LIMIT = 200005;

int tree[200005];
int arr[200005];

void update(int i,int val) {
    while(i<=LIMIT) {
        tree[i]+=val;
        i+=(i&-i);
    }
}

int query(int i) {
    int sum = 0;
    while(i>0) {
        sum+=tree[i];
        i-=(i&-i);
    }
    return sum;
}

int search(int i,int j,int val) {
    int k;
    int x;
    while(i<j) {
        k = (i+j)/2;
        x = query(k);
        if(x>=val) j = k;
        else i = k+1;
    }
    return i;
}

int main() {
    //freopen("in","r",stdin);
    int t,n,q,x;
    char c;
    scanf("%d",&t);
    for(int i = 1;i<=t;i++) {
        printf("Case %d:\n",i);
        memset(tree,0,sizeof(tree));
        scanf("%d %d",&n,&q);
        for(int j = 1;j<=n;j++) {
            scanf("%d",arr+j);
            update(j,1);
        }
        while(q--) {
            scanf(" %c %d",&c,&x);
            if(c == 'c') {
                int k = search(1,n+1,x);
                if(k<=n) {
                    update(k,-1);
                    printf("%d\n",arr[k]);
                } else {
                    printf("none\n");
                }
            } else {
                arr[++n] = x;
                update(n,1);
            }
        }
    }
    return 0;
}