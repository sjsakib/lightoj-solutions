/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-03 00:17:23
 * Problem   : 1196 - Inhabitants
 * CPU       : 0.124
 * Memory    : 3252
**/
#include <bits/stdc++.h>
#include <cstdio>

#define vi vector<int>
#define pi pair<int,int>

using namespace std;
struct Vec {
    long int x;
    long int y;
    Vec(long int x_,long int y_) {
        x = x_;
        y = y_;
    }
    Vec() {
        x = 0;
        y = 0;
    }
};

Vec v[100001];
int n,q;

Vec sub(Vec a,Vec b) {
    return Vec(a.x-b.x,a.y-b.y);
}
long int cross(Vec a,Vec b) {
    return a.x*b.y - b.x*a.y;
}

bool isIn(int x,int y) {
    Vec ab,oa,ac,c,a,b,o,oc;
    c = Vec(x,y);
    o = v[0];
    int low,mid,high;
    low = 0;
    high = n-1;
    long int line;
    while(low<=high) {
        mid = (low+high)/2;
        a = v[mid];
        b = v[(mid+1)%n];
        
        ab = sub(b,a);
        oa = sub(a,o);
        ac = sub(c,a);
        oc = sub(c,o);

        if(cross(ab,ac)<0) {
            return false;
        } else {
            line = cross(oa,oc);
            if(line>=0) {
                low = mid+1;
            } else if(line<0) {
                high = mid-1;
            }
        }
    }
    return true;
}

int main() {
    int t;
    scanf("%d",&t);
    int x,y;
    for(int i = 0;i<t;i++) {
        scanf("%d",&n);
        for(int j = 0;j<n;j++) {
            scanf("%d %d",&x,&y);
            v[j] = Vec(x,y);
        }
        scanf("%d",&q);
        printf("Case %d:\n",i+1);
        for(int j = 0;j<q;j++) {
            scanf("%d %d",&x,&y);
            if(isIn(x,y)) {
                printf("y\n");
            } else {
                printf("n\n");
            }
        }
    }
        
    return 0;
}
