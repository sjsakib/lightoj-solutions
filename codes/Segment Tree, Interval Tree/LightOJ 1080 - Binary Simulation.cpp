/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-11 12:00:54
 * Problem   : 1080 - Binary Simulation
 * CPU       : 0.216
 * Memory    : 2080
**/
#include <bits/stdc++.h>

using namespace std;

int BIT[100005];

void update(int arr[],int n,int i,int val) {
    while(i<=n) {
        arr[i]+=val;
        i+=i&-i;
    }
}

int getSum(int arr[],int n,int i) {
    int sum = 0;
    while(i>0) {
        sum+=arr[i];
        i-=i&-i;
    }
    return sum;
}

void inverse(int arr[],int n,int i,int j) {
    update(arr,n,i,1);
    update(arr,n,j+1,-1);
}



int main() {
    int t,n,q;
    int a,b;
    bool arr[100005];
    char c;
    scanf("%d\n",&t);
    for(int i = 0;i<t;i++) {
        n = 0;
        c = getchar();
        while(c != '\n') {
            arr[n++] = c - '0';
            c = getchar();
        }
        scanf("%d\n",&q);
        memset(BIT,0,sizeof(BIT[0])*n);
        printf("Case %d:\n",i+1);
        for(int j = 0;j<q;j++) {
            c = getchar();
            if(c == 'I') {
                scanf("%d %d\n",&a,&b);
                inverse(BIT,n,a,b);
            } else {
                scanf("%d\n",&a);
                printf("%d\n",(getSum(BIT,n,a)&1) ^ arr[a-1]);
            }
        }
    }
    return 0;
}