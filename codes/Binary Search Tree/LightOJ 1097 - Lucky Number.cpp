/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-20 00:40:58
 * Problem   : 1097 - Lucky Number
 * CPU       : 0.424
 * Memory    : 24412
**/
#include <bits/stdc++.h>

using namespace std;

int LIMIT = 1429431;

int tree[1429431*4];
int ans[100000+10];
int s;

void update(int at,int val,int L,int R,int pos) {
    if(L>pos || R<pos) return;
    if(L == R && L == pos) {
        tree[at]+=val;
        return;
    }
    int mid = (L+R)/2;
    update(at*2,val,L,mid,pos);
    update(at*2+1,val,mid+1,R,pos);
    tree[at] = tree[at*2]+tree[at*2+1];
}

int query(int at,int L,int R,int l,int r) {
    if(L>r || R<l) return 0;
    if(L>=l && R<=r) {
        return tree[at];
    }
    int mid = (L+R)/2;
    return query(at*2,L,mid,l,r)+query(at*2+1,mid+1,R,l,r);
}

int fnd(int at,int L,int R,int idx,bool erase = 0,int sum = 0) {
    if(L == R) {
        if(erase) tree[at] = 1;
        return L;
    }

    int ret;
    int mid = (L+R)/2;
    int left = at*2;
    int right = at*2+1;
    if(idx+tree[left]+sum>R) ret = 0;
    else if(idx+tree[left]+sum<=mid) ret = fnd(left,L,mid,idx,erase,sum);
    else ret =  fnd(right,mid+1,R,idx,erase,sum+tree[left]);
    tree[at] = tree[left]+tree[right];
    return ret;
}

void build() {
    ans[s++] = 1;
    int i = 2;
    int del = 0;
    while(i<=LIMIT) {
        fnd(1,1,LIMIT,i-del,1);
        i+=2;
        del++;
    }
    i = 2;
    while(i<=LIMIT) {
        int step = fnd(1,1,LIMIT,i,0);
        int k = step;
        ans[s++] = step;
        //cout<<step<<endl;
        if(!step) break;
        del = 0;
        while(k<=LIMIT) {
            fnd(1,1,LIMIT,k-del,1);
            del++;
            k+=step;
        }
        i++;
    }
}

int main() {
    //freopen("in","r",stdin);
    build();
    int t,idx;
    scanf("%d",&t);
    for(int i = 1;i<=t;i++) {
        scanf("%d",&idx);
        printf("Case %d: %d\n",i,ans[idx-1]);
    }
    return 0;
}